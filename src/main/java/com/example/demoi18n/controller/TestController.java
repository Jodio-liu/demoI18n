package com.example.demoi18n.controller;

import com.alibaba.fastjson.JSON;
import com.example.demoi18n.entity.UserItemPo;
import com.example.demoi18n.i18n_utils.I18nMessageUtil;
import com.example.demoi18n.mapper.UserItemMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private I18nMessageUtil i18nMessageUtil;
    @Resource
    private UserItemMapper userItemMapper;



    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        List<UserItemPo> userItemPos = userItemMapper.queryItemByUserId(123456);

        return new ResponseEntity<>(JSON.toJSONString(userItemPos), HttpStatus.OK);
    }

    @GetMapping("/pageHelper")
    public ResponseEntity<String> pageHelper() {
//        FireBaseUtil.pushSingle();

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("WStoHtml")
    public String WStoHtml() throws IOException {
        String body = "\n" +
                "<!DOCTYPE html><html lang=\"en\">  <head>    <meta charset=\"UTF-8\" />    <title>Planet J</title>    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />    <meta name=\"title\" content=\"Jodio  Invite You to Take a Photo Together\" />    <!-- iOS Facebook 回流 -->    <meta      property=\"al:ios:url\"      content=\"planetj://activity?token=c1bfee4b-43ff-4ace-829f-5375de225937\"    />    <meta property=\"al:ios:app_store_id\" content=\"1628776085\" />    <meta property=\"al:ios:app_name\" content=\"Planet J\" />  <!-- Android Facebook 回流 -->    <meta      property=\"al:android:url\"      content=\"planetj://activity?token=c1bfee4b-43ff-4ace-829f-5375de225937\"    />    <meta property=\"al:android:package\" content=\"com.j.planetj\" />    <meta property=\"al:android:app_name\" content=\"Planet J\" />    <!-- twitter分享 -->    <!-- https://developer.twitter.com/en/docs/twitter-for-websites/cards/guides/getting-started -->    <meta property=\"twitter:url\" content=\"www.ltjmeta.com\" />    <meta name=\"twitter:title\" content=\"Jodio  Invite You to Take a Photo Together\" />    <meta name=\"twitter:description\" content=\" I Actually Built a World! Come and See\" />    <meta name=\"twitter:site\" content=\"www.ltjmeta.com\" />    <meta name=\"twitter:card\" content=\"summary\" />    <meta name=\"twitter:image\" content=\"https://s2.ltjmeta.com/fs/a0000dp43.jpg\" />    <!-- facebook分享 -->    <!-- https://developers.facebook.com/docs/sharing/webmasters/ -->    <meta property=\"og:url\" content=\"www.ltjmeta.com\" />    <meta property=\"og:title\" content=\"Jodio  Invite You to Take a Photo Together\" />    <meta property=\"og:description\" content=\" I Actually Built a World! Come and See\" />    <meta property=\"og:image\" content=\"https://s2.ltjmeta.com/fs/a0000dp43.jpg\" />    <meta property=\"og:type\" content=\"website\" />    <style>      * {        margin: 0;        padding: 0;        box-sizing: border-box;        font-family: PingFang SC, Microsoft YaHei;      }      ::-webkit-scrollbar {        display: none;      }      .main {        height: 100vh;        display: flex;        flex-direction: column;        justify-content: center;        padding: 20px 8px 0;      }      .main #default {        /* 16px为padding, 宽高比为 416 / 352 */        height: calc((100vw - 16px) * 416 / 352);        display: flex;        display: none;        flex-direction: column;        align-items: center;        justify-content: space-between;      }      #open-app-btn {        background-color: #7a5aeb;        color: white;        width: 95%;        text-align: center;        margin: auto;        line-height: 50px;        border-radius: 25px;      }      .main #default .img-box {        width: 100%;        height: 100%;        position: relative;      }      .main #default .img-box .img {        border-radius: 10px;        display: block;        height: 100%;        width: 100%;        object-fit: cover;      }      .main #default .img-box .img-mask {        position: absolute;        bottom: 0;        border-radius: 0 0 10px 10px;        width: 100%;        padding: 15px 0 15px 15px;        background: rgba(0, 0, 0, 0.5);      }      .main #default .img-box .img-mask span {        display: block;        color: white;        font-size: 18px;      }      .main #default .img-box .img-mask span:not(:last-child) {        margin-bottom: 10px;      }      .main #default .img-box .img-mask .username {        font-size: 24px;      }      .main .failed {        width: fit-content;        margin: 0 auto;        height: 100%;        /* display: flex; */        display: none;        flex-direction: column;        justify-content: center;        align-items: center;      }      .main .failed .failed-icon {        width: 140px;        height: 140px;        object-fit: contain;        border: 0;      }      .main .failed .failed-msg {        margin-top: 20px;        font-size: 32px;        color: grey;        text-align: center;      }      .main #default .error {        width: 100%;        height: 200px;        line-height: 200px;        text-align: center;      }      .main #video-wrapper {        /* 16px为padding, 宽高比为 416 / 352 */        /* height: calc((100vw - 16px) * 416 / 352); */        margin: 0 auto;        width: 100%;        display: flex;        flex-direction: column;        justify-content: center;        display: none;      }      .main #video {        width: fit-content;        height: fit-content;        position: relative;        max-height: 100%;        margin: 0 auto;      }      .main #video-wrapper #video-player {        display: block;        max-width: 100%;        max-height: 100%;        border-radius: 8px;      }      .main #video-wrapper #video-mask {        width: 100%;        height: 100%;        position: absolute;        top: 0;        left: 0;        background-color: rgba(0, 0, 0, 0.3);        border-radius: 8px;      }      .main #video-wrapper #video-mask #video-play-btn {        width: 60px;        height: 60px;        position: absolute;        top: 50%;        left: 50%;        transform: translate(-50%, -50%);      }      .main #video-wrapper .video-bottom {        position: absolute;        bottom: 0;        border-radius: 0 0 8px 8px;        width: 100%;        padding: 15px 0 15px 15px;        background-color: rgba(0, 0, 0, 0.5);      }      .main #video-wrapper .video-bottom span {        display: block;        color: white;        font-size: 18px;      }      .main #video-wrapper .video-bottom span:not(:last-child) {        margin-bottom: 10px;      }      .main #video-wrapper .video-bottom .username {        font-size: 24px;      }    </style>  </head>  <body>    <div class=\"main\">      <div id=\"default\">        <div class=\"img-box\">          <img class=\"img\" id=\"show-img\" />          <div class=\"img-mask\">            <span class=\"username\" id=\"user\"></span>            <span id=\"desc\"></span>          </div>        </div>      </div>      <div id=\"video-wrapper\">        <div id=\"video\">          <video id=\"video-player\" loop></video>          <div class=\"video-bottom\">            <span class=\"username\" id=\"user-video\"></span>            <span id=\"desc-video\"></span>          </div>          <div id=\"video-mask\">            <img              src=\"https://ltjmeta.com/pages/sharemedia/img/icon-play.png\"              id=\"video-play-btn\"            />          </div>        </div>      </div>      <div id=\"open-app-btn\">Accept Invitation</div>      <!-- <div class=\"failed\">        <el-image class=\"failed-icon\" src=\"/assets/icon-alert.png\" />        <div class=\"failed-msg\">789</div>      </div> -->    </div>  </body>  <script src=\"https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js\"></script>  <!-- model page -->  <script src=\"https://www.ltjmeta.com/static/js/common.js\"></script>  <script src=\"https://www.ltjmeta.com/pages/sharemedia/js/index.js\"></script>  <!-- localhost -->  <!-- <script src=\"/static/js/common.js\"></script>  <script src=\"./js/index.js\"></script> --></html>";
//        Document doc = Jsoup.parse(body);
//        Elements es = doc.select("table");
//        for (Element element : es) {
//            element.html("123");//将table的内容替换为123
//        }
//        for (Element element : es) {
//            System.out.println(element.html());
//        }
//        System.out.println(doc.outerHtml());
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.println(doc.outerHtml());
        return body;
    }

}
