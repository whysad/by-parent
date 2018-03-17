package cn.hongtianren.entity;

import java.io.Serializable;

/**
 * 下拉框交互类
 * @Description vo
 * @author 谭震弘
 * @time 2017年12月31日
 * @version 1.0
 */
public class ComboboxModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6660723660014799866L;
	private Long id;
	private String text;

	public ComboboxModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
