import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISysForm } from 'app/shared/model/sys-form.model';
import { SysFormService } from './sys-form.service';
import { ISysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from 'app/entities/sys-user';

@Component({
    selector: 'jhi-sys-form-update',
    templateUrl: './sys-form-update.component.html'
})
export class SysFormUpdateComponent implements OnInit {
    sysForm: ISysForm;
    isSaving: boolean;

    sysusers: ISysUser[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected sysFormService: SysFormService,
        protected sysUserService: SysUserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sysForm }) => {
            this.sysForm = sysForm;
        });
        this.sysUserService.query().subscribe(
            (res: HttpResponse<ISysUser[]>) => {
                this.sysusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sysForm.id !== undefined) {
            this.subscribeToSaveResponse(this.sysFormService.update(this.sysForm));
        } else {
            this.subscribeToSaveResponse(this.sysFormService.create(this.sysForm));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysForm>>) {
        result.subscribe((res: HttpResponse<ISysForm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSysUserById(index: number, item: ISysUser) {
        return item.id;
    }
}
