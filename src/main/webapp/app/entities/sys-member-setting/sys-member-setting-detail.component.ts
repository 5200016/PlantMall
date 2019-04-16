import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysMemberSetting } from 'app/shared/model/sys-member-setting.model';

@Component({
    selector: 'jhi-sys-member-setting-detail',
    templateUrl: './sys-member-setting-detail.component.html'
})
export class SysMemberSettingDetailComponent implements OnInit {
    sysMemberSetting: ISysMemberSetting;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMemberSetting }) => {
            this.sysMemberSetting = sysMemberSetting;
        });
    }

    previousState() {
        window.history.back();
    }
}
