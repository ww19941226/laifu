package com.laifu.module.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
/**
* 生成分页相关的Tag
* 显示效果为上一页 1 3 4 5 6 9 下一页 [ ] 跳转
* @author Raindrops
* @version 2016/9/6
*/
public class NavigationTagV3 extends TagSupport{

	private static final long serialVersionUID = 1382261171261394934L;
	     
	/**
     * request 中用于保存Page<E> 对象的变量名,默认为“page”
     */
    private String bean = "page";
    
    /**
     * 分页跳转的url地址,此属性必须
     */
    private String url = null;
    
    /**
     * 显示页码数量
     */
    private int number = 5;
    
    /**
     * 是否优化分页,仅对主键为数值型的有效
     */
    private boolean optimize;
    
    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        Page<?> onePage = (Page<?>)pageContext.getRequest().getAttribute(bean); 
        if (onePage == null) 
            return SKIP_BODY;
        
        int curPage = onePage.getIndex();
        int pageCount = onePage.getContext().getPageCount();
        
        int mins = Math.max(curPage-2, 1);
        int maxn = Math.min(curPage+2, pageCount);

        maxn = Math.min(pageCount, curPage+(4-(curPage-mins)));
        mins = Math.max(1, curPage-(4-(maxn-curPage)));
        
        String context = "";
        String baseUrl = resolveUrl(url, pageContext);
        if(curPage != 1) context += "<a href='"+append(baseUrl, "pn", curPage-1)+ "'>上一页</a>";
        if(mins != 1) {
        	if(curPage==1) context += "1";
        	else context += "<a href='"+append(baseUrl, "pn", 1)+ "'>1</a>";
        }
        for(int i=mins; i<=maxn; i++) {
        	if(curPage == i) context += i;
        	else context += "<a href='"+append(baseUrl, "pn", i)+ "'>"+i+"</a>";
        }
        if(maxn != pageCount) {
        	if(curPage == maxn) context += pageCount;
        	else context += "<a href='"+append(baseUrl, "pn", pageCount)+ "'>"+pageCount+"</a>";
        }
        if(curPage != pageCount) context += "<a href='"+append(baseUrl, "pn", curPage+1)+ "'>下一页</a>";
        try {
			writer.print(context);
			writer.print("(共"+onePage.getContext().getTotal()+"条记录)");
			writer.print(makeNext(onePage, baseUrl ));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        /*
        url = resolveUrl(url, pageContext);
        Object firstModel = null;
        Object lastModel = null;

        if(onePage.getItems() != null && onePage.getItems().size() > 0) {
            firstModel = onePage.getItems().get(0);
            lastModel = onePage.getItems().get(onePage.getItems().size() - 1);
        }
        try {
            if (onePage.isHasPre()) {
                String preUrl = append(url, "pn", onePage.getIndex() - 1);
                if(optimize && firstModel != null) {
                    preUrl = append(preUrl, PageUtil.getIdName(firstModel), PageUtil.getIdValue(firstModel));
                    preUrl = append(preUrl, "pre", "true");
                }
                writer.print("<a href=\"" + preUrl + "\">上一页</a>&nbsp;");
                if(onePage.getIndex()-number/2 >= 2) {
                    writer.print("<a href=\""+append(url, "pn", 1)+"\">1</a>&nbsp;");
                }
            }
            //显示当前页码的前2页码和后两页码 
            //若1 则 1 2 3 4 5, 若2 则 1 2 3 4 5, 若3 则1 2 3 4 5,
            //若4 则 2 3 4 5 6 ,若10  则 8 9 10 11 12
            int currIndex = onePage.getIndex();
            int startIndex = (currIndex - 2 > 0)? currIndex - 2 : 1;  
            for(int i=1; i <= number && startIndex <= onePage.getContext().getPageCount(); startIndex++, i++) {
            	if(startIndex == currIndex) {
                    writer.print(startIndex);
                    continue;
                }
                String pageUrl  = append(url, "pn", startIndex);
                int offset = 0;
                long id = 0;
                if(optimize && firstModel != null && lastModel != null) {
                    if(startIndex < currIndex) {
                        //pre
                        pageUrl = append(pageUrl, "pre", "true");
                        offset = (startIndex - currIndex + 1) * onePage.getContext().getPageSize();
                        id = Long.valueOf(PageUtil.getIdValue(firstModel)) + offset;
                    }
                    else {
                        //next
                        offset = (startIndex - currIndex - 1) * onePage.getContext().getPageSize();
                        id = (Long.valueOf(PageUtil.getIdValue(lastModel)) + offset);
                    }
                    pageUrl = append(pageUrl, PageUtil.getIdName(lastModel), "" + id);
                }
                writer.print("<a href=\"" + pageUrl + "\">"+ startIndex +"</a>");
            }
            if (onePage.isHasNext()) {
                String nextUrl  = append(url, "pn", onePage.getIndex() + 1);
                if(optimize && lastModel != null) {
                    nextUrl = append(nextUrl, PageUtil.getIdName(lastModel), PageUtil.getIdValue(lastModel));
                }
                if(onePage.getContext().getPageCount()+1 - (onePage.getIndex()+number/2) >= 2) {
                    writer.print("<a href=\""+append(url, "pn", onePage.getContext().getPageCount())+"\">"+onePage.getContext().getPageCount()+"</a>&nbsp;");
                }
                writer.print("<a href=\"" + nextUrl + "\">下一页</a>");
            }
            writer.print("(共"+onePage.getContext().getTotal()+"条记录)");
            writer.print(makeNext(onePage, url));//跳转功能
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return SKIP_BODY;
    }
    
    /**
     * 为url 添加上下文环境.如果是登陆用户则还要添加uid参数
     * 
     * @param url
     * @param pageContext
     * @return
     * @throws javax.servlet.jsp.JspException
     */
    private String resolveUrl(String url, javax.servlet.jsp.PageContext pageContext) throws JspException{
        url = UrlSupport.resolveUrl(url, null, pageContext);

        url = url.replaceAll("&pn=\\d*", "").replaceAll("pn=\\d*", "").replaceAll("&pre=true", "").replaceAll("&id=\\d*", "").replaceAll("id=\\d*", "");

        return url;
    }
    
    
    private String append(String url, String key, int value) {

        return append(url, key, String.valueOf(value));
    }
    
    /**
     * 为url 参加参数对儿
     * 
     * @param url
     * @param key
     * @param value
     * @return
     */
    private String append(String url, String key, String value) {
        if (url == null || url.trim().length() == 0) {
            return "";
        }

        if (url.indexOf("?") == -1) {
            url = url + "?" + key + "=" + value;
        } else {
            if(url.endsWith("?")) {
                url = url + key + "=" + value;
            } else {
                url = url + "&amp;" + key + "=" + value;
            }
        }
        
        return url;
    }
    
    

    /**
     * @return the bean
     */
    public String getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(String bean) {
        this.bean = bean;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }
    /**
     * 生成跳转标签
     * @param page Page<?> 传入相应的Page
     * @param url String 请求url地址
     * @return String 生成标签的字符串
     */
    private String makeNext(Page<?> page, String url) {
    	String conStr = "?";
    	if(url.indexOf('?')!=-1) conStr = "&";
        StringBuffer sb = new StringBuffer("");
        sb
            .append(page.getIndex()).append("/").append(page.getContext().getPageCount())
            .append("页<input type='text' id='pn' size='3'/><a style='cursor:pointer;' onclick='location=\""+url+conStr+"pn=\"+document.getElementById(\"pn\").value'>")
            .append("跳转</a>")
        ;
        return sb.toString();
    }
}

