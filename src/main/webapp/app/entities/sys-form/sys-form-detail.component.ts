import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysForm } from 'app/shared/model/sys-form.model';

@Component({
    selector: 'jhi-sys-form-detail',
    templateUrl: './sys-form-detail.component.html'
})
export class SysFormDetailComponent implements OnInit {
    sysForm: ISysForm;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysForm }) => {
            this.sysForm = sysForm;
        });
    }

    previousState() {
        window.history.back();
    }
}
