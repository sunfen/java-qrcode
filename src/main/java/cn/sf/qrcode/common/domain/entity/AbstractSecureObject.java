package cn.sf.qrcode.common.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



/**
 * 通用数据访问DO
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/09/03
 */
@MappedSuperclass
public abstract class AbstractSecureObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
/*	
	@Convert(converter = BooleanIntegerConverter.class)
	@Column(nullable=false)
	@ColumnDefault("0")
	protected Boolean deleted = false;*/
	
	public AbstractSecureObject() {
		//this.deleted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

/*	public Boolean isDeleted() {
		return this.deleted;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	//	result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSecureObject other = (AbstractSecureObject) obj;
	/*	if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;*/
		
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    /** (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractSecureObject [id=" + id + "]";
    }
	
}