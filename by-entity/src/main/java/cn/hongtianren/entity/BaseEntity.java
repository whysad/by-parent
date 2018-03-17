package cn.hongtianren.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description 基本实体类
 * @author 谭震弘
 * @time 2017年12月30日
 * @version 1.0
 */
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1113706545563786738L;

	private Long id;
	/** 创建时间 */
	private Date create;
	/** 修改时间 */
	private Date modifyed;

	public BaseEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getModifyed() {
		return modifyed;
	}

	public void setModifyed(Date modifyed) {
		this.modifyed = modifyed;
	}

}
