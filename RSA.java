package rsa;
import java.math.*;

public class RSA {
	public static int gcd(int a, int b){
		if(b==0)
			return a;
		return gcd(b,(a%b));	
	}
	
	public static int primeGen(int n){
		do{
			double p=Math.random()*n;
			BigInteger b=new BigInteger(String.valueOf((int)p));	
			if(isPrime(b))
				return b.intValue();			
		}while(true);	
	}
	public static int[] Euclid(int a, int b){
	
	
		if(b==0){
			return new int[] {a,1,0};
		}
	
		
		int keys[]=Euclid(b, (a%b));	
		
		int x=keys[0];
		int y=keys[2];
		int z=keys[1]-(a/b)*keys[2];
		
	
		return new int[]{x,y,z};
		
	}
	public static BigInteger[] encrypt(String s,int publicKey1,int publicKey2){
		BigInteger[] m=new BigInteger[s.length()];
		int[] message=new int[s.length()];
		for(int i=0;i<s.length();i++){
			message[i]=(int)s.charAt(i);
	
			m[i]=new BigInteger(String.valueOf(message[i]));
		}
		BigInteger key1=new BigInteger(String.valueOf(publicKey1));
		BigInteger key2=new BigInteger(String.valueOf(publicKey2));
		
		BigInteger x;
		BigInteger[] encryptedChar=new BigInteger[message.length];
		for(int j=0;j<message.length;j++){
			x=m[j].modPow(key1,key2);
			
			encryptedChar[j]=x;
						
		}
		
		
		
		
		return encryptedChar;
	}
	public static String decrypt(BigInteger[]encrypt,int privateKey,int publicKey2){
		String s="";
		BigInteger [] decrypt=new BigInteger[encrypt.length];
		BigInteger privKey=new BigInteger(String.valueOf(privateKey));
		BigInteger key2=new BigInteger(String.valueOf(publicKey2));
		BigInteger x;
		int k;
		for(int i=0;i<encrypt.length;i++){
			x=encrypt[i].modPow(privKey, key2);
			decrypt[i]=x;
			k=decrypt[i].intValue();
			s+=(char)k;
		}
			
		return s;
	}
	public static boolean isPrime(BigInteger n){
		int m=n.intValue();
		if(m==1)
			return false;
		if(m==2)
			return true;
		if(m%2==0)
			return false;
		for(int i=3;i<(int)Math.sqrt(m);i++){
			if(m%i==0)
				return false;
		}
		
		return true;
	}
	public static int choosePublic(int fn){
		for(int i=fn;i>=1;i--){
			if(gcd(fn,i)==1)
				return i;
		}
		return 1;
	}
		public static void main (String[]args){
		System.out.println("enter your message");
		String t=IO.readString();
		int p=primeGen(5000); 
		int q=primeGen(5000);
		int n=p*q;
		int fn=(p-1)*(q-1);
		int key1=choosePublic(fn); 
		int[] keys=Euclid(key1,fn);
		int privatekey=keys[2]*fn+keys[1]*keys[0]; 
		String test="";
		int lala;
		BigInteger [] encryptedmessage=encrypt(t,key1,n);
		for(int i=0;i<encryptedmessage.length;i++){
			lala=encryptedmessage[i].intValue();
			test+=(char)lala;
		}
		System.out.println("encrypted: "+test);
		String s=decrypt(encryptedmessage,privatekey,n);
		System.out.println("decrypted: "+s);
			
	}
	
	
	
	
	
	
	
}
