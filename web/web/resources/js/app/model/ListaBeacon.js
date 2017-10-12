class ListaBeacon {
    constructor() {
        this._beaconList = []
    }

    get beaconList() {
        return [].concat(this._beaconList)
    }

    add(beacon) {
        this._beaconList.push(beacon)
    }

    esvazia() {
        this._beaconList = []
    }

    delete(id) {
        let newArray = []
        // console.log('dslknsdkgvnkps')
        this._beaconList.forEach(beacon => {
            if (id != beacon.id) {
                newArray.push(beacon)
            }
        })

        this._beaconList = newArray
    }
}