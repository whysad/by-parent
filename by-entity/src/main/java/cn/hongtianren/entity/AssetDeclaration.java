package cn.hongtianren.entity;

/**
 * 资产申报实体类
 * @Description 数据传输
 * @author 谭震弘
 * @time 2017年12月31日
 * @version 1.0
 */
public class AssetDeclaration extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6031205990372057171L;
	/**员工id*/
	private Long employeeId;
	/**员工姓名*/
	private String employee;
	/**资产id*/
	private Long assetId;
	/**资产名称*/
	private String asset;
	/**有效期*/
	private Integer term;
	/**分类*/
	private String category;
	/**状态*/
	private Integer status;

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	
	
}