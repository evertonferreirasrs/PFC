class BeaconView extends View {
    constructor(elemento) {
        super(elemento)
    }

    template(model) {
        return `
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>MAC</th>
                        <th>Coordenada X</th>
                        <th>Coordenada Y</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                    ${
                        model.beaconList.map(
                            beacon => `
                                <tr class="beacon" data-id="${beacon.id}">
                                    <td class="id-beacon">${beacon.id}</td>
                                    <td class="mac-beacon">${beacon.mac}</td>
                                    <td class="xCoordinate-beacon">${beacon.xCoordinate}</td>
                                    <td class="numeroyCoordinate-beacon">${beacon.yCoordinate}</td>
                                    <td class="actions-beacon">
                                        <center>
                                            <a class="btn btn-warning" href="${Configuration.getUrlWebApp()}beacon/alterar/${beacon.id}"><i class="fa fa-edit"></i></a>&nbsp;
                                            <a class="btn btn-danger" onclick="beaconController.delete(this)" href="#"><i class="fa fa-lg fa-trash"></i></a>
                                        </center>
                                    </td>
                                </tr>
                            `
                        ).join('')
                    }
                </tbody>
            </table>
        `
    }
}