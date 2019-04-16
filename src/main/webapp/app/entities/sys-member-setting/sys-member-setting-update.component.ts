import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISysMemberSetting } from 'app/shared/model/sys-member-setting.model';
import { SysMemberSettingService } from './sys-member-setting.service';

@Component({
    selector: 'jhi-sys-member-setting-update',
    templateUrl: './sys-member-setting-update.component.html'
})
export class SysMemberSettingUpdateComponent implements OnInit {
    sysMemberSetting: ISysMemberSetting;
    isSaving: boolean;
    createTime: string;
    updateTime: string;

    constructor(protected sysMemberSettingService: SysMemberSettingService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysMemberSetting }) => {
            this.sysMemberSetting = sysMemberSetting;
            this.createTime = this.sysMemberSetting.createTime != null ? this.sysMemberSetting.createTime.format(DATE_TIME_FORMAT) : null;
            this.updateTime = this.sysMemberSetting.updateTime != null ? this.sysMemberSetting.updateTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sysMemberSetting.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.sysMemberSetting.updateTime = this.updateTime != null ? moment(this.updateTime, DATE_TIME_FORMAT) : null;
        if (this.sysMemberSetting.id !== undefined) {
            this.subscribeToSaveResponse(this.sysMemberSettingService.update(this.sysMemberSetting));
        } else {
            this.subscribeToSaveResponse(this.sysMemberSettingService.create(this.sysMemberSetting));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysMemberSetting>>) {
        result.subscribe((res: HttpResponse<ISysMemberSetting>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
