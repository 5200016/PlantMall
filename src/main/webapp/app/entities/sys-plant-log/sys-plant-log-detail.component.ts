import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISysPlantLog } from 'app/shared/model/sys-plant-log.model';

@Component({
    selector: 'jhi-sys-plant-log-detail',
    templateUrl: './sys-plant-log-detail.component.html'
})
export class SysPlantLogDetailComponent implements OnInit {
    sysPlantLog: ISysPlantLog;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysPlantLog }) => {
            this.sysPlantLog = sysPlantLog;
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
