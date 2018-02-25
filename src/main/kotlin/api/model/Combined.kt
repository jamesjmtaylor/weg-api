package api.model

class Combined(val guns : List<Gun> = ArrayList(),
               val land : List<LandCombined> = ArrayList(),
               val sea : List<SeaCombined> = ArrayList(),
               val air : List<AirCombined> = ArrayList()) {}