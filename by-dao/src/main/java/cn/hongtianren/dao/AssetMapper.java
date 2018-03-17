package cn.hongtianren.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.hongtianren.entity.Asset;
import cn.hongtianren.entity.AssetDeclaration;
import cn.hongtianren.entity.AssetUse;
import cn.hongtianren.entity.ComboboxModel;


@Mapper
public interface AssetMapper {

	/**
	 * 查询所有资产申报信息
	 * 
	 * @param username
	 *            账号
	 * @return 资产申报信息
	 */
	List<AssetDeclaration> findDeclarationList(@Param("username") String username);

	/**
	 * 更新申报状态
	 * 
	 * @param id
	 *            申报id
	 * @param modifyed
	 *            修改时间
	 * @param status
	 *            申报状态
	 */
	@Update("update asset_declaration set modifyed=#{modifyed},status = #{status} where id = #{id}")
	void updateStatus(@Param("modifyed") Date modifyed, @Param("id") Long id, @Param("status") Integer status);

	/**
	 * 根据id查找申报信息
	 * 
	 * @param id
	 *            申报id
	 * @return 申报信息
	 */
	AssetDeclaration findDeclarationById(@Param("id") Long id);

	/**
	 * 更新资产使用状态
	 * 
	 * @param assetId
	 *            资产id
	 * @param modifyed
	 *            修改时间
	 * @param employeeId
	 *            员工id
	 */
	@Update("update asset set is_use=#{use},employee=#{employeeId},modifyed=#{modifyed} where id=#{assetId}")
	void updateAssertUse(@Param("use") Boolean use, @Param("modifyed") Date modifyed, @Param("assetId") Long assetId,
			@Param("employeeId") Long employeeId);

	/**
	 * 添加资产使用
	 * 
	 * @param assetUse
	 *            资产使用信息
	 */
	void saveUse(AssetUse assetUse);

	/**
	 * 根据账号查询资产信息
	 * 
	 * @param username
	 *            账号
	 * @return 资产信息
	 */
	List<Asset> findAssetByUsername(@Param("username") String username);

	/**
	 * 资产列表
	 * 
	 * @param name
	 *            资产名
	 * 
	 * @return 资产信息
	 */
	List<Asset> findAssetList(@Param("name") String name);

	/**
	 * 根据名字查询资产
	 * 
	 * @param name
	 *            资产名字
	 * @return
	 */
	List<Asset> findAssetListByName(String name);

	/**
	 * 添加申报信息
	 * 
	 * @param assetDeclaration
	 *            申报信息
	 */
	void saveDeclare(AssetDeclaration assetDeclaration);

	/**
	 * 查询所有资产类别
	 * 
	 * @return 所有资产类别
	 */
	@Select("select id,name AS text from category where is_show = 1")
	List<ComboboxModel> findCategories();

	/**
	 * 添加资产
	 * 
	 * @param asset
	 *            资产信息
	 */
	void saveAsset(Asset asset);

	/**
	 * 修改资产
	 * 
	 * @param asset
	 */
	@Update("update asset set modifyed=#{modifyed}, name=#{name},category=#{category} where id = #{id}")
	void updateAsset(Asset asset);

	/**
	 * 根据id删除资产
	 * 
	 * @param id
	 */
	@Delete("DELETE from asset WHERE id = #{id}")
	void deletedAssetById(@Param("id") Long id);

	/**
	 * 根据id查询资产信息
	 * 
	 * @param id
	 * @return
	 */
	Asset findAssetById(Long id);
}
