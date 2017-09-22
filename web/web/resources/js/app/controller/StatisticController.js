class StatisticController{
    constructor(){
        this._numberVisitors = $('#number-visitors')
    }

    async loadNumberVisitors(){
        let service = new StatisticService()
        let number = await service.getNumberVisitors()
        this._numberVisitors.text(number+' Visitante(s) Registrado(s)')
    }
}