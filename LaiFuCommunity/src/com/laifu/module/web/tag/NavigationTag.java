package com.laifu.module.web.tag;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 生成分页相关的Tag
 * 显示效果为上一页,下一页,跳转
 * @author Raindrops
 * @version 2016/9/1
 */
public class NavigationTag extends TagSupport {
    static final long serialVersionUID = 2372405317744358833L;
//  private static final Log LOGGER = LogFactory.getLog(NavigationTag.class);
    
    /**
     * request 中用于保存Page<E> 对象的变量名,默认为“page”
     */
    private String bean = "page";
    
    /**
     * 分页跳转的url地址,此属性必须
     */
    private String url = null;
    
    /**
     * 是否显示跳转
     */
    private boolean showJump = true;
    
    /**
     * 是否优化分页
     */
    private boolean optimize;
    
    /**
     * 根据属性生成标签
     * showJump为false时不生成跳转标签
     * @exception javax.servlet.jsp.JspException;
     */
    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        Page<?> onePage = (Page<?>)pageContext.getRequest().getAttribute(bean); 
        if (onePage == null) 
            return SKIP_BODY;
        
        url = resolveUrl(url, pageContext);//为url 添加上下文环境.如果是登陆用户则还要添加uid参数
        Object firstModel = null;
        Object lastModel = null;
        if(onePage.getItems() != null && onePage.getItems().size() > 0) {
            firstModel = onePage.getItems().get(0);
            lastModel = onePage.getItems().get(onePage.getItems().size() - 1);
        }
        try {
            if (onePage.isHasPre()) {
                String preUrl = append(url, "pn", onePage.getIndex() - 1);//上一页的地址
                if(optimize && firstModel != null) {
                    preUrl = append(preUrl, PageUtil.getIdName(firstModel), PageUtil.getIdValue(firstModel));
                    preUrl = append(preUrl, "pre", "true");
                }
                writer.print("<a href=\"" + preUrl + "\">上一页</a>;");//生成上一页超链接
            }
            if (onePage.isHasNext()) {
                String nextUrl  = append(url, "pn", onePage.getIndex() + 1);//下一页地址
                if(optimize && lastModel != null) {
                    nextUrl = append(nextUrl, PageUtil.getIdName(lastModel), PageUtil.getIdValue(lastModel));
                }
                writer.print("<a href=\"" + nextUrl + "\">下一页</a>");//生成下一页超链接
                
            }
            if(showJump) {
                writer.print(makeNext(onePage, url));//跳转功能
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
    
    /**
     * 为url 添加上下文环境.如果是登陆用户则还要添加uid参数
     * 
     * @param url String 请求参数
     * @param pageContext PageContext pageContext对象
     * @return 
     * @throws javax.servlet.jsp.JspException
     */
    private String resolveUrl(String url, javax.servlet.jsp.PageContext pageContext) throws JspException{
        url = UrlSupport.resolveUrl(url, null, pageContext);
        url = url.replaceAll("&pn=\\d*", "").replaceAll("&pre=true", "").replaceAll("&id=\\d*", "");
        return url;
    }
    
    
    /**
     * 将 url和key和value拼接起来
     * @param url String 请求地址
     * @param key String 
     * @param value String 
     * @return 生成相应字符串url?key=value&key=value
     */
    private String append(String url, String key, int value) {
        return append(url, key, String.valueOf(value));
    }
    
    /**
     * 为url 添加参数对儿
     * 
     * @param url String 请求的地址
     * @param key String ?key=value&key=value
     * @param value String ?key=value&key=value
     * @return String 返回标签的字符串
     */
    private String append(String url, String key, String value) {
        if (url == null || url.trim().length() == 0) {
            return "";
        }
        //-1 表示不存在
        if (url.indexOf("?") == -1) {
            url = url + "?" + key + "=" + value;
        } else {
            url = url + "&amp;" + key + "=" + value;
        }
        
        return url;
    }
    
    /**
     * 生成跳转标签
     * @param page Page<?> 传入相应的Page
     * @param url String 请求url地址
     * @return String 生成标签的字符串
     */
    private String makeNext(Page<?> page, String url) {
        StringBuffer sb = new StringBuffer("");
        sb
            .append(page.getIndex()).append("/").append(page.getContext().getPageCount())
            .append("页<input type='text' id='pn' size='3'/><a onclick='location=\""+url+"?pn=\"+document.getElementById(\"pn\").value'>")
            .append("跳转</a>")
        ;
        
        /*
        
        <input type="text" id="pn" size="3"/>
        
     	
        <a href="" onclick="
        	location=url+"?pn="document.getElementById("pn").value;
        ">
        	跳转
        </a>
        
        */
        
//        
//        StringBuffer sb = new StringBuffer("");
//        System.out.println(url);
//        sb.append("<br/><input type=\"text\" name=\"pn\" size=\"3\" value=\"1\" format=\"*N\"/>")
//            .append(page.getIndex()).append("/").append(page.getContext().getPageCount())
//            .append("页<anchor>跳转<a href=\"").append(url).append("\" method=\"get\" accept-charset=\"utf-8\">")
//        .append("<postfield name=\"pn\" value=\"$pn\"/></go></anchor><br/>");
//        ;
        
        
     
        return sb.toString();
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

    public void setShowJump(boolean showJump) {
        this.showJump = showJump;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }
    
}
