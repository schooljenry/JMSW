package test.thread1;

public class SynchronizedTest {
	
	private Byte[] lockObj = new Byte[0]; 
	/**
	 * 
	 * @param account	账号
	 * @param getMoney	数额
	 * @param execDate	时间
	 */
	public void opAccountMoney(String account, int getMoney, String execDate) {
		synchronized(lockObj){
		DataStore store = DataStore.getStore();
		
		UserTest user = store.getUserFromStore(account);

		if (user != null) {

			try {
				user = get(user, 1000);
				store.putUserInStore(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(user.getName() + "余额不足!");
			}

		}
	}
	}

	/**
	 * 
	 * @param user	用户对象
	 * @param money	数额
	 * @return
	 * @throws Exception
	 */
	public UserTest get(UserTest user, int money) throws Exception {
		if (user.getMoney() >= money) {
			sleepTest();
			user.setMoney(user.getMoney() - money);
			System.out.println("Thread.currentThread().getId() : "
					+ Thread.currentThread().getId() + "      "
					+ user.getName() + " 余额: " + user.getMoney());
		} else {
			throw new Exception();
		}
		return user;
	}

	/**
	 * 线程睡眠
	 */
	@SuppressWarnings("static-access")
	private void sleepTest() {
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
