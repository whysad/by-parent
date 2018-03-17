package cn.hongtianren.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.hongtianren.entity.Asset;
import cn.hongtianren.entity.AssetDeclaration;
import cn.hongtianren.entity.ComboboxModel;
/**
 * 
 * @Description 资产管理接口
 * @author 谭震弘
 * @time 2017年12月31日
 * @version 1.0
 */
public interface AssetService {

	/**
	 * 分页查询资产申报信息
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 * @param username
	 *            账号
	 * @return 资产申报分页信息
	 */
	PageInfo<AssetDeclaration> findDeclarationList(int pageNum, int pageSize, String username);

	/**
	 * 更新资产申报状态
	 * 
	 * @param id
	 *            申报id
	 * @param status
	 *            申报状态
	 */
	void updateStatus(Long id, Integer status);

	/**
	 * 获取个人资产信息
	 * 
	 * @param username
	 *            账号
	 * @param pageNumber
	 *            页数
	 * @param pageSize
	 *            页面大小
	 * @return 个人资产分页信息
	 */
	PageInfo<Asset> findAssetByUsername(String username, int pageNumber, int pageSize);

	/**
	 * 获取资产列表
	 * 
	 * @param pageNumber
	 *            页数
	 * @param pageSize
	 *            页面大小
	 * @return 资产分页列表
	 */
	PageInfo<Asset> findAssetList(int pageNumber, int pageSize,String name);

	/**
	 * 添加申报信息
	 * 
	 * @param username
	 *            账号
	 * @param asset
	 *            资产id
	 * @param term
	 *            使用时间
	 */
	void saveDeclare(String username, Long asset, Integer term);

	/**
	 * 查询所有资产类别
	 * 
	 * @return 资产类别
	 */
	List<ComboboxModel> findCategories();

	/**
	 * 添加资产
	 * 
	 * @param name
	 *            资产名称
	 * @param category
	 *            资产类别
	 */
	void saveAsset(String name, Long category);
	
	/**
	 * 编辑资产
	 */
	void updateAsset(Asset asset);
	
	/**
	 * 根据id查询资产信息
	 * @param id
	 * 			资产id
	 * @return
	 */
	Asset findAssetById(Long id);
	
	/**
	 * 根据id删除资产
	 * @param id
	 */
	void deletedAssetById(Long id);
	
	/**
	 * 根据名字查询资产
	 * @param name
	 * 			资产名字
	 * @return
	 */
	List<Asset> findAssetListByName(String name);
}
