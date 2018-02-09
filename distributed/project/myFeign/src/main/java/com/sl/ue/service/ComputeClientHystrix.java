package com.sl.ue.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ComputeClientHystrix implements ComputeClient{

	/* (non-Javadoc)
	 * @see com.chinamobo.myFeign.service.ComputeClient#add(java.lang.String, java.lang.String)
	 */
	@Override
	public String add(@RequestParam(value="a") String a, @RequestParam(value="b") String b) {
		// TODO Auto-generated method stub
		return "bew+ljksd";
	}

}
