//package org.wceng.client.proxy;
//
//import org.wceng.component.Bundler;
//import org.wceng.component.Crawler;
//import org.wceng.component.ProcessChain;
//import org.wceng.util.ProxyChecker;
//
//import java.net.Proxy;
//import java.util.List;
//
//public class ProxyClient {
//
//    public static void main(String[] args) {
//        ProcessChain chain = new ProcessChain("https://www.89ip.cn/");
//        chain.getConfig().setIgnoreCertValida(true);
//        chain.getConfig().setConnectTimeDelay(500);
//        chain.addProcess(ProxyUrlProcess.class);
//        chain.setDataListener(new ProxyListener());
//
//
//        Crawler.getInstance().addChain(chain).setup();
//    }
//
//    static class ProxyListener extends DataListener implements ProxyChecker.ValidateCallback {
//        ProxyChecker proxyChecker;
//
//        public ProxyListener() {
//            proxyChecker = new ProxyChecker();
//            proxyChecker.setValidateCallback(this);
//        }
//
//        @Override
//        public void afterLayerFetch(ProcessChecker checker, List<Bundler> bundlers) {
//            if (checker.is(0))
//                System.out.println("完成");
//        }
//
//        @Override
//        public void afterProcessFetch(ProcessChecker checker, Bundler bundler) {
//            if (checker.is(0)) {
//                for (String s : bundler.getStringList()) {
//                    String[] split = s.split(":");
//                    proxyChecker.validate(ProxyChecker.createHttpProxy(split[0], Integer.parseInt(split[1])));
//                }
//            }
//        }
//
//        @Override
//        public void onSuccess(Proxy proxy) {
//            System.out.println(proxy.address().toString().substring(1));
//        }
//
//        @Override
//        public void onFail(Proxy proxy) {
////            System.out.println("fail: " + proxy);
//        }
//    }
//
//}
