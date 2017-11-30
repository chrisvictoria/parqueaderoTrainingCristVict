package co.ceiba.parqueadero.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;

public class MotoBuilder {
	public static MotoEntity convertirAEntity(Moto moto){
		MotoEntity motoEntity = new MotoEntity();
		motoEntity.setPlaca(moto.getPlaca());
		motoEntity.setCilindraje(moto.getCilindraje());
		return motoEntity;
	}
	
	public static Moto convertirADominio(MotoEntity motoEntity){
		Moto moto = null;
		if(motoEntity != null){
			moto = new Moto(motoEntity.getPlaca(), motoEntity.getCilindraje());
		}
		return moto;
	}
	
	public static ArrayList<Moto> convertirListaADominio(List<MotoEntity> motoEntityList){
		ArrayList<Moto> motoList = new ArrayList<Moto>();
		if(motoEntityList != null){
			ArrayList<MotoEntity> array = new ArrayList<MotoEntity>(motoEntityList);
			for(MotoEntity entity : array){
				Moto moto = convertirADominio(entity);
				motoList.add(moto);
			}
		}
		return motoList;
	}
}
