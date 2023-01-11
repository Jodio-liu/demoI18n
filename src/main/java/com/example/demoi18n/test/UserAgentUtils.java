package com.example.demoi18n.test;


import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class UserAgentUtils {

    public static void main(String[] args) throws IOException {
        String str1 = "Mozilla/5.0 (Linux; Android 12; PGKM10 Build/SP1A.210812.016; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/108.0.5359.128 Mobile Safari/537.36 [FB_IAB/Orca-Android;FBAV/390.2.0.29.103;]";
        parseUserAgent(str1);
        String str2 = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/31.0.1650.18 Mobile/11B554a Safari/8536.25";
        parseUserAgent(str2);
        String str3 = "Mozilla/5.0 (Linux; U; Android 13; zh-cn; PGP110 Build/TP1A.220905.001) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.61 Mobile Safari/537.36 HeyTapBrowser/40.8.7.1";
        parseUserAgent(str3);
    }

    private static String parseUserAgent(String userAgent) {
        if(StringUtils.isEmpty(userAgent)){
            return null;
        }
        String u1 = userAgent.split("\\(")[1].split("\\)")[0];
        List<String> list = Lists.newArrayList(u1.split(";"));
        if(list.size() <=1){
            return null;
        }
        StringBuilder ua = new StringBuilder();
        if("U".equals(list.get(1).trim())){
            list.remove(3);
            list.remove(1);
        }
        int to = Math.min(3, list.size());
        List<String> subList = list.subList(0, to);
        for(String s : subList){
            ua.append(s.replace(" ", ""));
        }
        return String.valueOf(ua);
    }

    String s = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "  <head>\n" +
            "    <meta charset=\"UTF-8\" />\n" +
            "    <title>Planet J</title>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
            "    <!-- iOS Facebook 回流 -->\n" +
            "    <meta property=\"al:ios:url\" content=\"planetj://activity?token={token}\" />\n" +
            "    <meta property=\"al:ios:app_store_id\" content=\"1628776085\" />\n" +
            "    <meta property=\"al:ios:app_name\" content=\"Planet J\" />\n" +
            "    <meta property=\"og:title\" content=\"{title}\" />\n" +
            "\n" +
            "    <!-- Android Facebook 回流 -->\n" +
            "    <!-- <meta property=\"al:android:url\" content=\"sharesample://story/1234\">\n" +
            "    <meta property=\"al:android:package\" content=\"com.facebook.samples.sharesample\">\n" +
            "    <meta property=\"al:android:app_name\" content=\"Planet J\"> -->\n" +
            "\n" +
            "    <!-- twitter分享 -->\n" +
            "    <!-- https://developer.twitter.com/en/docs/twitter-for-websites/cards/guides/getting-started -->\n" +
            "    <meta property=\"twitter:url\" content=\"{url}\" />\n" +
            "    <meta name=\"twitter:title\" content=\"{title}\" />\n" +
            "    <meta name=\"twitter:description\" content=\"{desc}\" />\n" +
            "    <meta\n" +
            "      name=\"twitter:site\"\n" +
            "      content=\"{http://gg.chendahai.cn/static/share/index.html}\"\n" +
            "    />\n" +
            "    <meta\n" +
            "      name=\"twitter:card\"\n" +
            "      content=\"{summary_large_image | summary | app | player}\"\n" +
            "    />\n" +
            "    <meta\n" +
            "      name=\"twitter:image\"\n" +
            "      content=\"{http://gg.chendahai.cn/static/image/apple.jpg}\"\n" +
            "    />\n" +
            "\n" +
            "    <!-- facebook分享 -->\n" +
            "    <!-- https://developers.facebook.com/docs/sharing/webmasters/ -->\n" +
            "    <meta\n" +
            "      property=\"og:url\"\n" +
            "      content=\"{http://gg.chendahai.cn/static/share/index.html}\"\n" +
            "    />\n" +
            "    <meta property=\"og:title\" content=\"{This is my plan,let's play together}\" />\n" +
            "    <meta\n" +
            "      property=\"og:description\"\n" +
            "      content=\"{This is my plan,let's play together}\"\n" +
            "    />\n" +
            "    <meta\n" +
            "      property=\"og:image\"\n" +
            "      content=\"{http://gg.chendahai.cn/static/image/apple.jpg}\"\n" +
            "    />\n" +
            "    <meta property=\"og:type\" content=\"{}\" />\n" +
            "  </head>\n" +
            "\n" +
            "  <style>\n" +
            "    * {\n" +
            "      margin: 0;\n" +
            "      padding: 0;\n" +
            "      box-sizing: border-box;\n" +
            "      font-family: PingFang SC, Microsoft YaHei;\n" +
            "    }\n" +
            "\n" +
            "    ::-webkit-scrollbar {\n" +
            "      display: none;\n" +
            "    }\n" +
            "\n" +
            "    #open-app-btn {\n" +
            "      background-color: #7a5aeb;\n" +
            "      color: white;\n" +
            "      width: 95%;\n" +
            "      text-align: center;\n" +
            "      line-height: 50px;\n" +
            "      border-radius: 25px;\n" +
            "      height: 50px;\n" +
            "      position: absolute;\n" +
            "      bottom: 0;\n" +
            "      left: 50%;\n" +
            "      transform: translateX(-50%);\n" +
            "    }\n" +
            "\n" +
            "    #main {\n" +
            "      position: relative;\n" +
            "      display: flex;\n" +
            "      flex-direction: column;\n" +
            "    }\n" +
            "\n" +
            "    #avatar {\n" +
            "      display: block;\n" +
            "      height: 100%;\n" +
            "      object-fit: cover;\n" +
            "    }\n" +
            "  </style>\n" +
            "\n" +
            "  <body>\n" +
            "    <div id=\"main\">\n" +
            "      <img id=\"avatar\" />\n" +
            "      <div id=\"open-app-btn\">Accept Invitation</div>\n" +
            "    </div>\n" +
            "  </body>\n" +
            "\n" +
            "  <script src=\"https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js\"></script>\n" +
            "  <script>\n" +
            "    ;(async () => {\n" +
            "      function generateGUID() {\n" +
            "        return \"xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx\".replace(\n" +
            "          /[xy]/g,\n" +
            "          function (c) {\n" +
            "            var r = (Math.random() * 16) | 0,\n" +
            "              v = c == \"x\" ? r : (r & 0x3) | 0x8\n" +
            "            return v.toString(16)\n" +
            "          }\n" +
            "        )\n" +
            "      }\n" +
            "      let guid = localStorage.getItem(\"guid\")\n" +
            "      if (!guid) {\n" +
            "        guid = generateGUID()\n" +
            "        localStorage.setItem(\"guid\", guid)\n" +
            "      }\n" +
            "\n" +
            "      const testHost = \"https://chloe.itest1.jcncluster.jcndev.com\"\n" +
            "      const releaseHost = \"https://chloe.ltjmeta.com\"\n" +
            "      const testStatsHost = \"http://2stats-test.j.cn\"\n" +
            "      const releaseStatsHost = \"https://stats.ltjmeta.com/commonStats\"\n" +
            "      const androidDownloadUrl =\n" +
            "        \"https://play.google.com/store/apps/details?id=com.j.planetj\"\n" +
            "      const iosDownloadUrl =\n" +
            "        \"https://apps.apple.com/us/app/planet-j/id1628776085\"\n" +
            "      const host = location.href.includes(\"test.html\") ? testHost : releaseHost\n" +
            "      const statsHost = location.href.includes(\"test.html\")\n" +
            "        ? testStatsHost\n" +
            "        : releaseStatsHost\n" +
            "      const api = `${host}/api/fetchShareInfoH5`\n" +
            "      const apiV2 = `${host}/api/fetchShareInfoV2`\n" +
            "      const accessType = 1\n" +
            "      const token = new URLSearchParams(location.search).get(\"token\")\n" +
            "      // const v = query.get(\"v\")\n" +
            "\n" +
            "      const isAndroid = navigator.userAgent.indexOf(\"Android\") > -1\n" +
            "      const isIOS =\n" +
            "        navigator.userAgent.indexOf(\"iPhone\") > -1 ||\n" +
            "        navigator.userAgent.indexOf(\"iPad\") > -1\n" +
            "      const clientEnv = {\n" +
            "        app: \"unity\",\n" +
            "        deviceBrand: \"\",\n" +
            "        deviceModel: \"\",\n" +
            "        jcnappid: \"\",\n" +
            "        jcnuserid: \"\",\n" +
            "        net: \"wifi\",\n" +
            "        system: isAndroid ? \"android\" : isIOS ? \"ios\" : \"other\",\n" +
            "        systemVersion: isAndroid ? \"android\" : isIOS ? \"ios\" : \"other\",\n" +
            "        v: \"\",\n" +
            "        latitude: 0,\n" +
            "        longitude: 0,\n" +
            "        marketChannel: \"unity\",\n" +
            "        testSwitch: 0,\n" +
            "        modelAccuracy: \"high\",\n" +
            "        rom: \"unity\",\n" +
            "        abis: \"[arm64]\",\n" +
            "      }\n" +
            "\n" +
            "      let source\n" +
            "      let timer\n" +
            "\n" +
            "      // setTimeout 会清除 timer, 如果 timer 存在说明已点击且 setTimeout 还未执行(即已成功打开 app)\n" +
            "      window.addEventListener(\"blur\", () => {\n" +
            "        if (timer) {\n" +
            "          clearTimeout(timer)\n" +
            "          timer = undefined\n" +
            "        }\n" +
            "      })\n" +
            "\n" +
            "      const btn = document.getElementById(\"open-app-btn\")\n" +
            "      const appScheme = \"planetj://activity\"\n" +
            "\n" +
            "      try {\n" +
            "        const { data: response } = await axios.post(api, { token })\n" +
            "        const { data: resData, bizStatus, bizMessage } = response\n" +
            "        const { originality, shareCarrier, shareLocation, shareType } =\n" +
            "          resData.originalityParams ?? {}\n" +
            "\n" +
            "        btn.onclick = () => {\n" +
            "          axios.post(statsHost, {\n" +
            "            action: \"count_h5\",\n" +
            "            subItemId: originality,\n" +
            "            itemId: shareType,\n" +
            "            typeId: \"Shareprofile_click\",\n" +
            "            ext: guid,\n" +
            "            from: shareLocation,\n" +
            "            clientEnv,\n" +
            "          })\n" +
            "\n" +
            "          navigator.clipboard?.writeText(`#${token}#`)\n" +
            "          location.href = appScheme\n" +
            "          // onblur 中会清除 timer，如果500ms内没有 blur，说明用户没有成功跳转app，可以跳转到下载页\n" +
            "          timer = setTimeout(() => {\n" +
            "            if (timer) {\n" +
            "              timer = undefined\n" +
            "              if (isAndroid) {\n" +
            "                location.href = androidDownloadUrl\n" +
            "                axios.post(statsHost, {\n" +
            "                  action: \"count_h5\",\n" +
            "                  subItemId: originality,\n" +
            "                  itemId: shareType,\n" +
            "                  typeId: \"Shareprofile_store\",\n" +
            "                  ext: guid,\n" +
            "                  from: shareLocation,\n" +
            "                  clientEnv,\n" +
            "                })\n" +
            "                axios.post(apiV2, {\n" +
            "                  token,\n" +
            "                  accessType,\n" +
            "                })\n" +
            "              } else if (isIOS) {\n" +
            "                location.href = iosDownloadUrl\n" +
            "                axios.post(statsHost, {\n" +
            "                  action: \"count_h5\",\n" +
            "                  subItemId: originality,\n" +
            "                  itemId: shareType,\n" +
            "                  typeId: \"Shareprofile_store\",\n" +
            "                  ext: guid,\n" +
            "                  from: shareLocation,\n" +
            "                  clientEnv,\n" +
            "                })\n" +
            "                axios.post(apiV2, {\n" +
            "                  token,\n" +
            "                  accessType,\n" +
            "                })\n" +
            "              } else {\n" +
            "                alert(\"请使用手机下载\")\n" +
            "              }\n" +
            "            }\n" +
            "          }, 500)\n" +
            "        }\n" +
            "\n" +
            "        if (bizStatus !== 0) {\n" +
            "          if (bizMessage) {\n" +
            "            alert(bizMessage)\n" +
            "          } else {\n" +
            "            alert(\"Invalid token\")\n" +
            "          }\n" +
            "\n" +
            "          return\n" +
            "        }\n" +
            "\n" +
            "        const img = document.getElementById(\"avatar\")\n" +
            "        img.src = resData.imgUrl\n" +
            "        source = resData.source\n" +
            "        // img.src = \"https://s2.j.cn/ch/t000xbi7w.jpg\"\n" +
            "        // img.src = \"https://s2.j.cn/ch/p000mxrxv.jpg\"\n" +
            "        document.getElementById(\"main\").style.height = `${window.innerHeight}px`\n" +
            "        axios.post(statsHost, {\n" +
            "          action: \"count_h5\",\n" +
            "          subItemId: originality?.toString(),\n" +
            "          itemId: shareType?.toString(),\n" +
            "          typeId: \"Shareprofile_visit\",\n" +
            "          ext: guid,\n" +
            "          from: shareLocation?.toString(),\n" +
            "          clientEnv,\n" +
            "        })\n" +
            "      } catch (e) {\n" +
            "        alert(\"error\")\n" +
            "        throw e\n" +
            "      }\n" +
            "    })()\n" +
            "  </script>\n" +
            "</html>\n";


}

