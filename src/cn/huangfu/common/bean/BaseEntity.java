package cn.huangfu.common.bean;

import cn.huangfu.common.annotation.Id;

import java.io.Serializable;
import java.util.Date;


/***
 * 实体基础依赖对象
 * @author Demon
 *
 * @param <ID>
 */
//@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@Id
	protected ID id;

	/** 创建者ID */
	protected String createById;

	/** 创建者 */
	protected String createBy;

	/** 创建时间 */
	protected Date createTime;

	/** 更新者 */
	protected String updateBy;

	/** 更新时间 */
	protected Date updateTime;

	/** 备注 */
	protected String remark;

	/** 0：未删除  1:已删除 */
	protected String del="0";

	/*public ID getId() {
		return id;
	}*/

	public void setId(ID id) {
		this.id = id;
	}

	public String getCreateById() {
		return createById;
	}

	public void setCreateById(String createById) {
		this.createById = createById;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}
}
