##### getActivity()空指针问题  

● 问题描述  
可能是遇到内存重启的问题了，也就是说fragment经历过onDetach方法；  

● 解决办法  
声明全局变量 mActivity，在onAttach方法中给mActivity初始化；  

