package test.thread1;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
	private static Map<String , UserTest>  mapCache = new HashMap<String, UserTest>();

	private static DataStore store;
	
	private static Byte[] lockObj1 = new Byte[0];
	private static Byte[] lockObj2 = new Byte[0];
	
	private DataStore() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Ϊ�˱�֤���ݵ�ͬ��,��DataStore���е�������ҶԲ�����������ͬ��
	 * @return
	 */
	public static DataStore getStore(){
		if(store == null){
			synchronized (lockObj1) {
				if(store == null){
					store = new DataStore();
				}
			}
		}
		return store;
	}
	
	/**
	 * д���û����󵽻�����
	 * @param user
	 */
	public void putUserInStore(UserTest user){
		mapCache.put(user.getAccount(), user);
		//DB.saveOrUpdate(user);
	}
	
	/*
	 * 
	 * 
	 */
	/**
	 * �������в�ѯ�Ķ���ʹӻ�����ȡ��,û�оͲ�ѯ���ݿ�
	 * ��������û�в�ѯ����,���Ⲣ��ʱ��β�ѯ���ݿ�,�������ͬ��
	 * @param account	�˺�
	 * @return
	 */
	public UserTest getUserFromStore(String account){
		UserTest user = mapCache.get(account);
		if(user == null){
			//Ϊ���������������,��Ҫ���ַ���������Ϊ������
			synchronized (lockObj2) {
				user = mapCache.get(account);
				if(user == null){
					//���ݿ��ѯ db.query(account)
					if("zhangsan".equalsIgnoreCase(account)){
						user =new UserTest(1L , "zhangsan"  , 10000 ,"����" );
					}
					if("lisi".equalsIgnoreCase(account)){
						user = new UserTest(2L , "lisi"  , 10000 ,"����" );
					}
					putUserInStore(user);
					/*** if continue do no synchronized somethings need : ***/
					//lockObj2.notify();
					//do something................
				}
			}
		}
		return user;
	}
}
