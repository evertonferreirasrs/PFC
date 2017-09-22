class StatisticService{
    constructor(){

    }

    async getNumberVisitors(){
        let httpService = new HttpService()

        let numberVisitors = await httpService.get(Configuration.getUrl()+'estatistica/numberVisitors')

        return numberVisitors
    }
}