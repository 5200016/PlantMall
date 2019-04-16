import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';

@Component({
    selector: 'jhi-sys-member-level-detail',
    templateUrl: './sys-member-level-detail.component.html'
})
export class SysMemberLevelDetailComponent implements OnInit {
    sysMemberLevel: ISysMemberLevel;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMemberLevel }) => {
            this.sysMemberLevel = sysMemberLevel;
        });
    }

    previousState() {
        window.history.back();
    }
}
