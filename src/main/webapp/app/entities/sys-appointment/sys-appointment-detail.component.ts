import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysAppointment } from 'app/shared/model/sys-appointment.model';

@Component({
    selector: 'jhi-sys-appointment-detail',
    templateUrl: './sys-appointment-detail.component.html'
})
export class SysAppointmentDetailComponent implements OnInit {
    sysAppointment: ISysAppointment;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysAppointment }) => {
            this.sysAppointment = sysAppointment;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
