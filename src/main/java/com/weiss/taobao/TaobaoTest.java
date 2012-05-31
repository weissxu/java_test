package com.weiss.taobao;

import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.TaobaokeItemsConvertRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.TaobaokeItemsConvertResponse;

public class TaobaoTest {

	@Test
	public void testTaobaoke() throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12663096",
				"a257a5381cc8e3329a611f0bcfd90785", Constants.FORMAT_XML);
		TaobaokeItemsConvertRequest req = new TaobaokeItemsConvertRequest();
		req.setFields("num_iid,click_url,iid,commission,commission_rate,commission_num,commission_volume");
		req.setNick("hz0799");
		req.setOuterCode("bbs");
		req.setNumIids("1,2,3");
		req.setPid(123456L);
		req.setIsMobile(true);
		TaobaokeItemsConvertResponse response = client.execute(req);
		System.out.println(response.getBody());
	}

	// http://gw.api.taobao.com/router/rest?sign=AD93C1A4FEF303AB271DF0E08A356BAA&timestamp=2012-05-25+13%3A26%3A01&v=2.0&app_key=12129701&method=taobao.item.get&partner_id=top-apitools&format=xml&num_iid=16594848265&fields=detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,desc,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual
	@Test
	public void testTaobao() throws ApiException {
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12663096",
				"a257a5381cc8e3329a611f0bcfd90785", Constants.FORMAT_XML);
		ItemGetRequest req = new ItemGetRequest();
		req.setFields("detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual");
		req.setNumIid(16427684009L);
		ItemGetResponse response = client.execute(req, null);
		System.out.println(response.getBody());
	}
}
