package com.jt.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.anno.CacheFind;
import com.jt.exception.ServiceException;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemParamItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParamItem;
import com.jt.vo.JsonPage;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemMapper itemMapper;
	@Resource
	//商品规格和商品的关系
	private ItemParamItemMapper itemParamItemMapper;

	@Resource
	//商品描述表
	private ItemDescMapper itemDescMapper;

	//分页查询所有商品列表
	@CacheFind(parKey = "ITEM_PAGE_ROWS",seconds = 10)
	public JsonPage<Item> queryAll(Integer page, Integer rows) {
		long t1 = System.currentTimeMillis();
		Page<Item>   pageObj= new Page(page,rows);
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.orderByDesc("created");
		IPage<Item> itemIPage = itemMapper.selectPage(pageObj, queryWrapper);
		long t2 = System.currentTimeMillis();
		System.out.println("查询商品花费了："+(t2-t1)+"秒");
		return new JsonPage((int) itemIPage.getTotal(),itemIPage.getRecords());
	}

	//批量下架商品
	public SysResult instock(Integer[] ids){
		Item item = new Item();
		item.setStatus(2).setUpdated(new Date());
		return upDate(ids, item);
	}


	//批量上架 商品 1
	public SysResult reshelf(Integer[] ids){
		Item item = new Item();
		item.setStatus(1).setUpdated(new Date());
		return upDate(ids, item);
	}

	/**
	 * /基于ID批量修改方法
	 * @param ids
	 * @param item
	 * @return
	 */
	private SysResult upDate(Integer[] ids, Item item) {
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id",ids);
		Integer update = itemMapper.update(item, updateWrapper);
		SysResult sysResult = new SysResult();
		if (update>0){
			sysResult.setStatus(200);
		}
		return sysResult;
	}
	/**
	 * 批量删除 根据ID
	 */
	@Transactional
	public Integer deleteByIds(Long[] ids){

		//删除商品规格的参数!!
		QueryWrapper<ItemParamItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("item_id",ids);
		itemParamItemMapper.delete(queryWrapper);

		//删除商品的 描述表
		QueryWrapper<ItemDesc> descQueryWrapper = new QueryWrapper<>();
		descQueryWrapper.in("item_id",ids);
		itemDescMapper.delete(descQueryWrapper);

		//删除商品 本身
		List<Long> idsList = Arrays.asList(ids);
		if (itemMapper.deleteBatchIds(idsList)<1){
			throw new ServiceException("商品 删除 失败");
		}

		return 1;
	}

	/**
	 * 根据 itemId  查询单个的所有信息
	 * @param id itemId
	 * @return
	 */
	public Item queryByIdOne(Integer id){
		return itemMapper.selectById(id);
	}

	/**
	 * 根据 ID 更新单条
	 */
	@Transactional
	public void updateOneById(Item item){

		//更新 商品信息
		item.setUpdated(new Date());
		if (itemMapper.updateById(item)<1){
			throw new ServiceException("更新商品信息失败");
		}

		//更新商品描述
		ItemDesc itemDesc =new ItemDesc();
		itemDesc.setItemId(item.getId()).setItemDesc(item.getItemDesc()).setUpdated(new Date());
		if (itemDescMapper.updateById(itemDesc)<1){//没有商品描述
			itemDesc.setCreated(new Date());
			if (itemDescMapper.insert(itemDesc)<1){//新增商品描述
				throw new ServiceException("更新商品描述失败");
			}
		}

		//更新商品与商品规格参数描述表
		ItemParamItem itemParamItem = new ItemParamItem();
		itemParamItem.setItemId(item.getId()).setParamData(item.getItemParams()).setUpdated(new Date());
		QueryWrapper queryWrapper =new QueryWrapper();
		queryWrapper.eq("item_id",item.getId());
		if (itemParamItemMapper.update(itemParamItem, queryWrapper)<1){
			itemParamItem.setCreated(new Date());
			if (itemParamItemMapper.insert(itemParamItem)<1){
				throw new ServiceException("更新商品规格 参数失败");
			}
		}

	}


	/**
	 * /item/save 新增单条
	 */
	@Transactional	//开启事务
	public void saveItemOne(Item item) {
		if (StringUtils.isEmpty(item.getCid())){
			throw new ServiceException("没有选择类目");
		}
		item.setUpdated(new Date()).setCreated(new Date());
		//新增到商品表
		if (itemMapper.insert(item) <1){
			throw new ServiceException("新增商品信息失败");
		}


		//创建商品规格参数 对象
		ItemParamItem itemParamItem = new ItemParamItem();
		itemParamItem.setItemId(item.getId()).setParamData(item.getItemParams()).setCreated(new Date()).setUpdated(new Date());
		//新增到描述信息
		if (itemParamItemMapper.insert(itemParamItem)<1){
			throw new ServiceException("新增商品参数信息失败");
		}

		//将商品描述信息 放入 商品 desc表 tb_item_desc
		//创建商品描述表
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId()).setItemDesc(item.getItemDesc()).setCreated(new Date()).setUpdated(new Date());
		if (itemDescMapper.insert(itemDesc)<1){
			throw new ServiceException("新增商品描述信息失败");
		}


	}


	/**
	 * 获取 item 的商品描述信息
	 * @return
	 */
	public ItemDesc queryItemDescByItemId(Long itemId){
		ItemDesc itemDesc = itemDescMapper.selectById(itemId);
		if (itemDesc == null){
			throw new ServiceException("未找到 商品详情");
		}
		return itemDesc;
	}

	/*==============================处理跨域请求================================================================================*/




}
