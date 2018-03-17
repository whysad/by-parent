package cn.hongtianren.entity;

import java.util.Date;

public class AssetUse extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3538906824747857479L;

	private Long assetDeclaration;

    private Long employee;

    private Long asset;

    private Date expireDate;

    public Long getAssetDeclaration() {
        return assetDeclaration;
    }

    public void setAssetDeclaration(Long assetDeclaration) {
        this.assetDeclaration = assetDeclaration;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getAsset() {
        return asset;
    }

    public void setAsset(Long asset) {
        this.asset = asset;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}