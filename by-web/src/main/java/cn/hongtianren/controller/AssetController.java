package cn.hongtianren.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.hongtianren.entity.Asset;
import cn.hongtianren.entity.AssetDeclaration;
import cn.hongtianren.entity.ComboboxModel;
import cn.hongtianren.service.AssetService;
import net.sourceforge.web.easyui.EasyuiGridModel;

@Controller
public class AssetController {

	@Autowired
	private AssetService assetService;

	@GetMapping("/asset_approval")
	public String assetApproval() {
		return "assetApproval";
	}

	@GetMapping("/asset_declaration")
	public String assetDeclaration() {
		return "assetDeclaration";
	}
	
	@GetMapping("/my_asset")
	public String myAsset(){
		return "myAsset";
	}
	
	@GetMapping("/categories")
	@ResponseBody
	public List<ComboboxModel> categories(){
		return assetService.findCategories();
	}
	@GetMapping("/my_asset_data")
	@ResponseBody
	public EasyuiGridModel<Asset> findMyAsset(int page, int rows){
		String username = (String)SecurityUtils.getSubject().getPrincipal();
		PageInfo<Asset> info = assetService.findAssetByUsername(username,page,rows);
		EasyuiGridModel<Asset> easyuiGridModel = new EasyuiGridModel<>();
		easyuiGridModel.setRows(info.getList());
		easyuiGridModel.setTotal((int)info.getTotal());
		return easyuiGridModel;
	}
	
	@GetMapping("/asset_list")
	public String assetList(){
		return "assetList";
	}
	@GetMapping("/add_asset")
	public String addAsset(){
		return "addAsset";
	}
	
	@GetMapping("/update_asset")
	public String  updateAsset(String id,ModelMap map) {
		Asset asset = assetService.findAssetById(Long.valueOf(id));
		map.addAttribute("asset", asset);
		return "updateAssert";
	}
	
	@GetMapping("/validateName")
	@ResponseBody
	public boolean validateName(String name){
		boolean result = false;
		List<Asset> list = assetService.findAssetListByName(name);
		if (list.size()>0) {
			result = true;
		}
		return result;
	}
	
	@GetMapping("/assets")
	@ResponseBody
	public EasyuiGridModel<Asset> assetList(int page, int rows,String name) {
		PageInfo<Asset> assets = assetService.findAssetList(page, rows,name);
		EasyuiGridModel<Asset> easyuiGridModel = new EasyuiGridModel<>();
		easyuiGridModel.setTotal((int) assets.getTotal());
		easyuiGridModel.setRows(assets.getList());
		return easyuiGridModel;
	}

	@GetMapping("/declarations")
	@ResponseBody
	public EasyuiGridModel<AssetDeclaration> declarationList(int page, int rows) {
		Subject subject = SecurityUtils.getSubject();
		String username = null;
		if (!subject.hasRole("manager")) {
			username = (String) subject.getPrincipal();
		}
		PageInfo<AssetDeclaration> declarationList = assetService.findDeclarationList(page, rows, username);
		EasyuiGridModel<AssetDeclaration> easyuiGridModel = new EasyuiGridModel<>();
		easyuiGridModel.setTotal((int) declarationList.getTotal());
		easyuiGridModel.setRows(declarationList.getList());
		return easyuiGridModel;
	}
	
	@PostMapping("/update_asset")
	@ResponseBody
	public void updateAsset(Asset asset){
		assetService.updateAsset(asset);
	}
	
	@PostMapping("/save_asset")
	@ResponseBody
	public void saveAsset(String name,Long category){
		assetService.saveAsset(name,category);
	}
	
	@PostMapping("/deleted_Asset")
	@ResponseBody
	public void deletedAsset(Long id){
		assetService.deletedAssetById(id);;
	}
	
	@PostMapping("/declare")
	@ResponseBody
	public void saveDeclare(Long asset,Integer term){
		if(asset == 0){
			throw new RuntimeException("参数异常");
		}
		if(term==0){
			term = null;
		}
		Subject subject = SecurityUtils.getSubject();
		String username = (String)subject.getPrincipal();
		assetService.saveDeclare(username,asset,term);
	}

	@PostMapping("/update_approval_status")
	@ResponseBody
	public void updateApprovalStatus(Long id, Integer status) {
		if (id == null || status == null) {
			throw new RuntimeException("参数错误");
		}
		assetService.updateStatus(id, status);
	}
	
	
}
