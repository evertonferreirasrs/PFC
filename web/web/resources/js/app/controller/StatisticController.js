class StatisticController{
    constructor(){
        this._numberVisitors = $('#number-visitors')
    }

    async loadNumberVisitors(){
        let service = new StatisticService()
        let number = await service.getNumberVisitors()
        this._numberVisitors.text(number+' Visitante(s) Registrado(s)')
    }

    async loadDataBarChart(day){
        let service = new StatisticService()
        let dia1 = [['FAITEC 2018', 'Quantidade Visitantes']]

        dia1.push(['19:00', await service.count({'datahora_gt':new Date(2017, 9, day, 19, 0, 0, 0).getTime(), 'datahora_lt':new Date(2017, 9, day, 19, 59, 0, 0).getTime()})])
        dia1.push(['20:00', await service.count({'datahora_gt':new Date(2017, 9, day, 20, 0, 0, 0).getTime(), 'datahora_lt':new Date(2017, 9, day, 20, 59, 0, 0).getTime()})])
        dia1.push(['21:00', await service.count({'datahora_gt':new Date(2017, 9, day, 21, 0, 0, 0).getTime(), 'datahora_lt':new Date(2017, 9, day, 21, 59, 0, 0).getTime()})])
        dia1.push(['22:00', await service.count({'datahora_gt':new Date(2017, 9, day, 22, 0, 0, 0).getTime(), 'datahora_lt':new Date(2017, 9, day, 22, 59, 0, 0).getTime()})])
        
        return dia1
    }
}