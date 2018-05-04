package com.nd.adhoc.dmsdk.api;

public interface IDeviceSystem  {

	public void powerOff();

	public void sleep();

	public void wake();

	public void remoteDormancy();

	public void remoteApp();

	public void getRemoteAppList();

	public boolean installApp(String apkFile);

	public void uninstallApp();

	public void remoteService();

	/**
	 * cpu 使用率
	 * @param packageName
	 * @return
	 */
	public long getApplicationCpuUsage(String packageName);
	/**
	 * ram 使用率
	 * @param packageName
	 * @return
	 */
	public long getApplicationRamUsage(String packageName);
	/**
	 * 应用程序的数据大小
	 * @param packageName
	 * @return
	 */
	public long getApplicationDataSize(String packageName);
	/**
	 * 缓存大小
	 * @param packageName
	 * @return
	 */
	public long getApplicationCacheSize(String packageName);

	/**
	 * 应用程序是否运行中
	 * @param packageName
	 * @return
	 */
	public boolean isApplicationRunning(String packageName);

	/**
	 * 获取应用程序包名
	 * @return
	 */
	public String [] getAppInstalllist();

}
