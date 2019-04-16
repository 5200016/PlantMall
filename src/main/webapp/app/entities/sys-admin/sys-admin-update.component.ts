import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysAdmin } from 'app/shared/model/sys-admin.model';
import { SysAdminService } from './sys-admin.service';

@Component({
    selector: 'jhi-sys-admin-update',
    templateUrl: './sys-admin-update.component.html'
})
export class SysAdminUpdateComponent implements OnInit {
    sysAdmin: ISysAdmin;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(protected sysAdminService: SysAdminService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysAdmin }) => {
            this.sysAdmin = sysAdmin;
            this.createTime = this.sysAdmin.createTime != null ? this.sysAdmin.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysAdmin.updateTime != null ? this.sysAdmin.updateTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysAdmin.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysAdmin.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysAdmin.id !== undefined) {
            this.subscribeToSaveResponse(this.sysAdminService.update(this.sysAdmin));
        } else {
            this.subscribeToSaveResponse(this.sysAdminService.create(this.sysAdmin));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysAdmin>>) {
        result.subscribe((res: HttpResponse<ISysAdmin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
