import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';
import { SysMemberLevelService } from './sys-member-level.service';

@Component({
    selector: 'jhi-sys-member-level-update',
    templateUrl: './sys-member-level-update.component.html'
})
export class SysMemberLevelUpdateComponent implements OnInit {
    sysMemberLevel: ISysMemberLevel;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(protected sysMemberLevelService: SysMemberLevelService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysMemberLevel }) => {
            this.sysMemberLevel = sysMemberLevel;
            this.createTime = this.sysMemberLevel.createTime != null ? this.sysMemberLevel.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysMemberLevel.updateTime != null ? this.sysMemberLevel.updateTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysMemberLevel.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysMemberLevel.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysMemberLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.sysMemberLevelService.update(this.sysMemberLevel));
        } else {
            this.subscribeToSaveResponse(this.sysMemberLevelService.create(this.sysMemberLevel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysMemberLevel>>) {
        result.subscribe((res: HttpResponse<ISysMemberLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
