package com.fjsh.rpc.statistic.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fjsh.rpc.statistic.enums.AIAbtestStatusEnums;
import com.fjsh.rpc.statistic.service.IstatisticFacet;

public class StatisticFacetService implements IstatisticFacet {
	private ConcurrentHashMap<String, Long> facetConcurrent=new ConcurrentHashMap<String, Long>();
	private static final Log logger = LogFactory.getLog(StatisticFacetService.class);
	public ConcurrentHashMap<String, Long> getFacetConcurrent() {
		return facetConcurrent;
	}

	public void setFacetConcurrent(ConcurrentHashMap<String, Long> facetConcurrent) {
		this.facetConcurrent = facetConcurrent;
	}

	public void setFacet(String point, long times) {
		// TODO Auto-generated method stub
		if(null==point||point.trim().equals(""))
		{
			return ;
		}
		Long temp=facetConcurrent.get(point);
		temp=temp==null?0:temp;
		facetConcurrent.put(point, temp+times);
	}

	public void printlog() {
		// TODO Auto-generated method stub
		StringBuilder sBuffer=new StringBuilder();
		//进行平均值处理
		Long count=facetConcurrent.get(AIAbtestStatusEnums.alltimeavg.getType());
		count=count==null?1:count;		 
       for (AIAbtestStatusEnums status : AIAbtestStatusEnums.values()) {    	   
    	   if(null!=facetConcurrent.get(status.getType()))
    	   {
    		   if(status.getType().equals("0-1"))
    		   {
    			   //单独统计次数
        		   sBuffer.append(status.getDescription()+":"+facetConcurrent.get(status.getType())+"次  ");
        		   continue;
    		   }    		   
    		   sBuffer.append(status.getDescription()+":"+facetConcurrent.get(status.getType())+"ms  "+status.getDescription()+"(平均耗时):"+facetConcurrent.get(status.getType())/count+"ms  ");
    	   }			
		}
       logger.info(sBuffer.toString());
       facetConcurrent.clear();
       sBuffer=null;
	}

	public void clean() {
		// TODO Auto-generated method stub
		facetConcurrent.clear();
	}	
}
