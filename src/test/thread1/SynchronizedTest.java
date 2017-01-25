package test.thread1;

public class SynchronizedTest {
	
	private Byte[] lockObj = new Byte[0]; 
	/**
	 * 
	 * @param account	�˺�
	 * @param getMoney	����
	 * @param execDate	ʱ��
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
				System.out.println(user.getName() + "����!");
			}

		}
	}
	}

	/**
	 * 
	 * @param user	�û�����
	 * @param money	����
	 * @return
	 * @throws Exception
	 */
	public UserTest get(UserTest user, int money) throws Exception {
		if (user.getMoney() >= money) {
			sleepTest();
			user.setMoney(user.getMoney() - money);
			System.out.println("Thread.currentThread().getId() : "
					+ Thread.currentThread().getId() + "      "
					+ user.getName() + " ���: " + user.getMoney());
		} else {
			throw new Exception();
		}
		return user;
	}

	/**
	 * �߳�˯��
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
