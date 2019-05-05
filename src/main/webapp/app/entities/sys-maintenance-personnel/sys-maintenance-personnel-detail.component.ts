import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';

@Component({
    selector: 'jhi-sys-maintenance-personnel-detail',
    templateUrl: './sys-maintenance-personnel-detail.component.html'
})
export class SysMaintenancePersonnelDetailComponent implements OnInit {
    sysMaintenancePersonnel: ISysMaintenancePersonnel;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMaintenancePersonnel }) => {
            this.sysMaintenancePersonnel = sysMaintenancePersonnel;
        });
    }

    previousState() {
        window.history.back();
    }
}
