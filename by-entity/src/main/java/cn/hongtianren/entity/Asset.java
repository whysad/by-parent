package cn.hongtianren.entity;

import java.util.Date;

/**
 * 资产实体类
 * 
 * @Description DTO
 * @author 谭震弘
 * @time 2017年12月31日
 * @version 1.0
 */
public class Asset extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -312608898229901534L;

	private String name;

	private String category;

	private Long categoryId;

	private String employee;

	private String image;

	private Boolean isUse;

	private Boolean isDestroy;

	private Date expireDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public Boolean getIsDestroy() {
		return isDestroy;
	}

	public void setIsDestroy(Boolean isDestroy) {
		this.isDestroy = isDestroy;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}