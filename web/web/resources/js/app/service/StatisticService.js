class StatisticService{
    constructor(){

    }

    async getNumberVisitors(){
        let httpService = new HttpService()

        let numberVisitors = await httpService.get(Configuration.getUrl()+'estatistica/numberVisitors')

        return numberVisitors
    }

    async count(parameters){
        let httpService = new HttpService()
        
        let stringParam = ''

        if(parameters.datahora_lt != null){
            stringParam += 'datahora_lt='+parameters.datahora_lt
        }

        if(parameters.datahora_gt != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'datahora_gt='+parameters.datahora_gt
        }

        if(parameters.datahora_eq != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'datahora_eq='+parameters.datahora_eq
        }

        if(parameters.posicaoX_eq != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'posicaoX_eq='+parameters.posicaoX_eq
        }

        if(parameters.posicaoX_lt != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'posicaoX_lt='+parameters.posicaoX_lt
        }

        if(parameters.posicaoX_gt != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'posicaoX_gt='+parameters.posicaoX_gt
        }

        if(parameters.posicaoY_eq != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'posicaoY_eq='+parameters.posicaoY_eq
        }

        if(parameters.posicaoY_lt != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'posicaoY_lt='+parameters.posicaoY_lt
        }

        if(parameters.posicaoY_gt != null){
            if(stringParam.length > 0){
                stringParam += '&'
            }
            stringParam += 'posicaoY_gt='+parameters.posicaoY_gt
        }

        let result = await httpService.get(Configuration.getUrl()+'estatistica/count?'+stringParam)
        
        return result
    }
}