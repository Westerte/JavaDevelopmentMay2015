package edu.nesterenko.touroperator.entity;

public class Client extends BusinessEntity {
	private int id;    
	private String nickName;
	private String password;
	private String email;
	private ClientType clientType;		

	public Client(int id, String nickName, String password, String email,
			String clientType) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.clientType = ClientType.valueOf(clientType);
	}

	public int getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d login: %s password: %s email: %s clientType: %s"
				, id, nickName, password, email, clientType);
	}
}
