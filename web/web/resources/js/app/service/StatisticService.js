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

        let result = await httpService.get(Configuration.getUrl()+'estatistica/count?'+stringParam)
        
        return result
    }
}