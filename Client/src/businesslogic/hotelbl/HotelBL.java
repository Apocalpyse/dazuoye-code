package businesslogic.hotelbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.hotelbusinesslogicservice.HotelBusinessLogicService;
import dataservice.hoteldataservice.HotelDataService;

import dataservice.hoteldataservice.HotelFactory;
import dataservice.orderdataservice.OrderDataService;

import dataservice.orderdataservice.OrderFactory;
import dataservice.promotiondataservice.PromotionFactory;
import dataservice.userdataservice.UserDataService;

import dataservice.promotiondataservice.PromotionDataService;

import dataservice.userdataservice.UserFactory;
import po.CustomerPO;
import po.Evaluation;
import po.HotelPO;
import po.HotelPromotionPO;
import po.MemberPromotionPO;
import po.OrderPO;
import po.UserPO;
import po.WebPromotionPO;
import po.OrderState;
import po.PromotionPO;
import vo.CustomerVO;
import vo.HotelVO;

public class HotelBL implements HotelBusinessLogicService {

	private HotelDataService hds;
    private OrderDataService ods;
    private PromotionDataService pds;
    private UserDataService uds;

	public HotelBL() throws RemoteException{
		try{
			HotelFactory hotelFactory=(HotelFactory) Naming.lookup("rmi://127.0.0.1:1234/hotelFactory");
			this.hds=hotelFactory.createHotelDataService();
			OrderFactory orderFactory=(OrderFactory) Naming.lookup("rmi://127.0.0.1:1234/orderFactory");
			this.ods=orderFactory.createOrderDataService();
			PromotionFactory promotionFactory=(PromotionFactory) Naming.lookup("rmi://127.0.0.1:1234/promotionFactory");
			this.pds=promotionFactory.createPromotionDataService();
			UserFactory userFactory=(UserFactory) Naming.lookup("rmi://127.0.0.1:1234/userFactory");
			this.uds=userFactory.createUserDataService();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
    public boolean createHotel(HotelVO hv) throws RemoteException{
    	return hds.insert(new HotelPO(hv.getID(),hv.getHotelManager(),hv.getHotelManPhone(),
				hv.getGoal(),hv.getPrice(),hv.getHotelName(),hv.getTradeArea(),
				hv.getHotelLocation(),hv.getHotelPhone(),hv.getStars(),hv.getInstruction(), 
				hv.getEvaluation(),hv.getCooperatateCompany()));
    }
    @Override
    public HotelVO getHotel(long id) throws RemoteException{
        HotelPO po=this.hds.find(id);
        return new HotelVO(po.getID(), po.getHotelManager(), po.getHotelManPhone(),
    			po.getGoal(), po.getPrice(), po.getHotelName(), po.getTradeArea(), 
    			po.getHotelLocation(), po.getHotelPhone(),po.getStars(), po.getInstruction(),
    			po.getEvaluation(),po.getCooperatateCompany());
    }
    @Override
	public boolean changeHotel(HotelVO hv)throws RemoteException{
		return this.hds.update(new HotelPO(hv.getID(),hv.getHotelManager(),hv.getHotelManPhone(),
				hv.getGoal(),hv.getPrice(),hv.getHotelName(),hv.getTradeArea(),
				hv.getHotelLocation(),hv.getHotelPhone(),hv.getStars(),hv.getInstruction(), 
				hv.getEvaluation(),hv.getCooperatateCompany()));
	}
    @Override
	public boolean changeGoal(HotelVO hv,Evaluation eva)throws RemoteException{
		HotelPO hp2=this.hds.find(hv.getID());
		ArrayList<Evaluation> arr=hp2.getEvaluation();
		arr.add(eva);
		double d1 = 0,d2;
		for(int i=0;i<arr.size();i++){
			d1=0+arr.get(i).getMark();
		}
		d2=d1/arr.size();
		return this.hds.update(new HotelPO(hv.getID(),hv.getHotelManager(),hv.getHotelManPhone(),
				d2,hv.getPrice(),hv.getHotelName(),hv.getTradeArea(),
				hv.getHotelLocation(),hv.getHotelPhone(),hv.getStars(),hv.getInstruction(), 
				hv.getEvaluation(),hv.getCooperatateCompany()));
	}
    @Override
	public boolean changePassWord(String account,String pw)throws RemoteException{
		UserPO up;
		up=this.uds.find(account);
		if(pw==up.getPassword()){
			return true;
		}
		else{
			return false;
		}
		
	}
	
    @Override
	public boolean addEvaluation(long id,ArrayList evaluation)throws RemoteException{
		HotelPO hp=this.hds.find(id);
		return this.hds.update(new HotelPO(hp.getID(),hp.getHotelManager(),hp.getHotelManPhone(),
				hp.getGoal(),hp.getPrice(),hp.getHotelName(),hp.getTradeArea(),
				hp.getHotelLocation(),hp.getHotelPhone(),hp.getStars(),hp.getInstruction(), 
				evaluation,hp.getCooperatateCompany()));
	}
    @Override
    public boolean changeOraderState(long id,OrderState os)throws RemoteException{
		OrderPO op=this.ods.find(id);
		 return this.ods.update(new OrderPO(op.getId(),op.getCustomerName(),op.getCustomerPhone(),op.getHotelName(),op.getHotelPhone(),op.getHotelLocation()
			        ,op.getRoomType(),op.getRoomNumber(),op.getRoomPrice(),op.getDiscount(),op.getStartTime(),op.getEndTime(),op.getExecuteTime(),op.getRoomPrice()*op.getDiscount()*op.getRoomNumber(),os
			        ,op.getIsExistChild(),op.getCustomerNumber(),op.getMasterId()));

    }
    @Override
    public boolean setPromotionOne(long id,int numberOfRoom,double discount)throws RemoteException{
    	HotelPromotionPO hpo =this.pds.find2(id);
    	return this.pds.update2(new HotelPromotionPO(id,hpo.getPromotionName(),hpo.getCreatedTime(),
    			hpo.getUsageState(),hpo.getBeginTime(),hpo.getEndTime(),hpo.getMemberType(),hpo.getDiscount(),
    			numberOfRoom,discount,hpo.getDiscountforlargeramount(),hpo.getDiscountforlargeramount(),
    			hpo.getBirthDiscount(),hpo.getCompanyDiscount(),hpo.getHotelID()));
    	    
    }
    @Override
    public boolean setPromotionTwo(long id,double discount)throws RemoteException{
    	HotelPromotionPO hpo =this.pds.find2(id);
    	return this.pds.update2(new HotelPromotionPO(id,hpo.getPromotionName(),hpo.getCreatedTime(),
    			hpo.getUsageState(),hpo.getBeginTime(),hpo.getEndTime(),hpo.getMemberType(),hpo.getDiscount(),
    			hpo.getDiscountForMoreRoom(),hpo.getDiscountformoreroom(),hpo.getDiscountforlargeramount(),hpo.getDiscountforlargeramount(),
    			discount,hpo.getCompanyDiscount(),hpo.getHotelID()));
    }
    @Override
    public boolean setPromotionThree(long id,String beginTime,String endTime)throws RemoteException{
    	HotelPromotionPO hpo =this.pds.find2(id);
    	return this.pds.update2(new HotelPromotionPO(id,hpo.getPromotionName(),hpo.getCreatedTime(),
    			hpo.getUsageState(),beginTime,endTime,hpo.getMemberType(),hpo.getDiscount(),
    			hpo.getDiscountForMoreRoom(),hpo.getDiscountformoreroom(),hpo.getDiscountforlargeramount(),hpo.getDiscountforlargeramount(),
    			hpo.getBirthDiscount(),hpo.getCompanyDiscount(),hpo.getHotelID()));
    }
    @Override
    public boolean setPromotionFour(long id,double discount)throws RemoteException{
    	HotelPromotionPO hpo =this.pds.find2(id);
    	return this.pds.update2(new HotelPromotionPO(id,hpo.getPromotionName(),hpo.getCreatedTime(),
    			hpo.getUsageState(),hpo.getBeginTime(),hpo.getEndTime(),hpo.getMemberType(),hpo.getDiscount(),
    			hpo.getDiscountForMoreRoom(),hpo.getDiscountformoreroom(),hpo.getDiscountforlargeramount(),hpo.getDiscountforlargeramount(),
    			hpo.getBirthDiscount(),discount,hpo.getHotelID()));
    }
   
    public long findMaxId() throws RemoteException{
        return this.hds.findMaxId();
    }
	
    
}
