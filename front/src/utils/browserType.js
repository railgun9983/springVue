export function getBrowserType() {
  let sBrowser = 0;
  let sUsrAg = window.navigator.userAgent;
  // The order matters here, and this may report false positives for unlisted browsers.
  if (sUsrAg.indexOf("Firefox") > -1) {
    sBrowser = 1;
    // 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:61.0) Gecko/20100101 Firefox/61.0'
  } else if (sUsrAg.indexOf("Opera") > -1 || sUsrAg.indexOf("OPR") > -1) {
    sBrowser = 2;
    // 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 OPR/57.0.3098.106'
  } else if (sUsrAg.indexOf("Trident") > -1) {
    sBrowser = 3;
    // 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; Zoom 3.6.0; wbx 1.0.0; rv:11.0) like Gecko'
  } else if (sUsrAg.indexOf("Edge") > -1) {
    sBrowser = 4;
    // 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299'
  } else if (sUsrAg.indexOf("Chrome") > -1) {
    sBrowser = 5;
    // 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/66.0.3359.181 Chrome/66.0.3359.181 Safari/537.36'
  } else if (sUsrAg.indexOf("Safari") > -1) {
    sBrowser = 6;
    // 'Mozilla/5.0 (iPhone; CPU iPhone OS 11_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1 980x1306'
  } else {
    sBrowser = 7;
  }
  return sBrowser;
}
