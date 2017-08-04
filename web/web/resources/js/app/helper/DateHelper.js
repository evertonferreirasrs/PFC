class DateHelper{
    constructor(){
        throw new Error("A classe DateHelper não pode ser estanciada.")
    }

    static getStringFromDate(data){
        let hour = data.getHours()
        let minutes = data.getMinutes()

        if(hour < 10){
            hour = hour.toString().padStart(2, "0")
        }

        if(minutes < 10){
            minutes = minutes.toString().padStart(2, "0")
        }
        return `${data.toLocaleDateString('pt-BR')} - ${hour}:${minutes}`
    }

    static getTimeFromString(stringDate){
        if(!/\d{2}\/\d{2}\/\d{4}\s-\s\d{2}:\d{2}/.test(stringDate)) 
            throw new Error('Deve estar no formato dd/mm/aaaa - HH:mm')

        let dateTime = stringDate.split(' ')

        let date = dateTime[0].split('/').reverse().map((item, indice) => item - indice % 2)

        let time = dateTime[2].split(':')
        date.push(parseInt(time[0]))
        date.push(parseInt(time[1]))
     
        return new Date(date[0], date[1], date[2], date[3], date[4], 0, 0).getTime()
    }
}