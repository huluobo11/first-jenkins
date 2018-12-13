package org.util.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.util.tools.DateTimeUtil;
import org.util.tools.Logs;


public abstract class BaseService<T>{
	public abstract PagingAndSortingRepository<T, Long> getEntityDao();
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public abstract boolean isEntityUnique(T oldEntity, T newEntity) throws ServiceException;	

	public abstract void toUpdateStatus(int status,Long id) throws ServiceException;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<T> queryEntity(Map<String, Object> searchParams, PageRequest pageRequest, Class<T> entityClazz){
		Specification<T> spec = buildSpecification(searchParams, entityClazz);
		return ((JpaSpecificationExecutor)getEntityDao()).findAll(spec, pageRequest);		
	}
	public void save(T e) throws ServiceException{
		getEntityDao().save(e);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public T get(Long id){
		return getEntityDao().findById(id).get();
	}
	
	public void delete(Long id) throws ServiceException{
		getEntityDao().deleteById(id);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> getAll() {
		return (List<T>) getEntityDao().findAll();
	}
	
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page queryEntity(Map<String, Object> searchParams, PageRequest pageRequest,Class entityClazz, PagingAndSortingRepository dao){
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Object> spec = DynamicSpecifications.bySearchFilter(filters.values(), entityClazz);
		return ((JpaSpecificationExecutor)dao).findAll(spec, pageRequest);		
	}

	public void save(Object e, PagingAndSortingRepository dao) throws ServiceException{
		dao.save(e);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Object get(Long id,PagingAndSortingRepository dao){
		return dao.findById(id);
	}

	public void delete(Long id, PagingAndSortingRepository dao) throws ServiceException{
		dao.delete(id);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List getAll(PagingAndSortingRepository dao){
		return (List) dao.findAll();
	}
	


	
	
	/**
	 * 创建查询分页对象
	 * @param pageNumber 页号
	 * @param pagzSize 页大小
	 * @param sortBy 用于排序的属性名(如:id)
	 * @param sort 排序的方向(如:desc)
	 * @return
	 */
	public PageRequest buildPageRequest(int pageNumber, int pageSize, String sortBy, String sort) {
		Sort theSort = null;
		if(sortBy==null){sortBy = "id";}
		if(sort==null){sort = "desc";}
		if("desc".equalsIgnoreCase(sort)){
			theSort = new Sort(Direction.DESC, sortBy);
		}else{
			theSort = new Sort(Direction.ASC, sortBy);
		}
		return new PageRequest(pageNumber - 1, pageSize, theSort);
	}

	/**
	 * 创建动态查询组合条件.
	 */
	public Specification<T> buildSpecification(Map<String, Object> searchParams, Class<T> entityClazz) {
		Map<String, SearchFilter> filters = SearchFilter.parse(modifyDateParams(searchParams,entityClazz));
		Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), entityClazz);
		return spec;
	}
	public Map<String,Object> modifyDateParams(Map<String, Object> searchParams,Class<T> entityClazz){
		Map<String,Object> newParams = new HashMap<String,Object>();
		try{
			for(Map.Entry<String, Object> entry : searchParams.entrySet()) {
				if(entry.getValue()==null || StringUtils.isEmpty(entry.getValue().toString())){
					continue;
				}
				if(isDate(entry.getKey(),entityClazz)){
					String qryKey = entry.getKey();
					String qryValue = entry.getValue().toString();
					if((qryKey.startsWith("LT_") || qryKey.startsWith("LTE_")) && qryValue.length()==10){
						newParams.put(entry.getKey(), DateTimeUtil.parse(entry.getValue().toString()+" 23:59:59"));
					}else{
						newParams.put(entry.getKey(), DateTimeUtil.parse(entry.getValue().toString()));
					}
				}else{
					newParams.put(entry.getKey(), entry.getValue());
				}
			}
		}catch(Exception ex){
			Logs.error(ex);
		}
		return newParams;
	}
	
//	public static boolean isDate(String propName,Class clazz){
//		String[] pns = propName.split("_");
//        Field[] field = clazz.getDeclaredFields();
//        for(int i=0;i<field.length;i++){
//        	String name = field[i].getName();
//        	if(name.equals(pns[1])){
//        		
//        	}
//        	 
//        	String type = field[i].getGenericType().toString().toLowerCase();
//        	if("class java.util.date".equalsIgnoreCase(type)){
//    			return true;
//        	}
//        }
//        return false;
//	}
//	
	public static boolean isDate(String propertyName, Class clazz){
    	try{
    		if(propertyName.indexOf("_")>0){
    			propertyName = propertyName.substring(propertyName.indexOf("_")+1);
    		}
    		if(propertyName.indexOf(".")>0){
	    		String n1 = propertyName.substring(0,propertyName.indexOf(".", 0));
	    		String n2 = propertyName.substring(propertyName.indexOf(".", 0)+1);
	    		Field f = clazz.getDeclaredField(n1);
		        return isDate(n2,f.getType());
    		}else{	    		
    			Field f = clazz.getDeclaredField(propertyName);
            	String type = f.getGenericType().toString().toLowerCase();
            	if("class java.util.date".equalsIgnoreCase(type)){
        			return true;
            	}else{
            		return false;
            	}
    		}
    	}catch(Exception ex){
    		Logs.error(ex);
    	}        
        return false;
	}
	
	protected PrincipalCollection getCurrentUser(){
		PrincipalCollection user = null;
    	try{
			Subject subject= SecurityUtils.getSubject();
			user = subject.getPrincipals();
		}catch(Exception ex){
			Logs.error(ex);
		}
    	return user;
	}
		
	
}
