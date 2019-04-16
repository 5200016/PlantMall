import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';

@Component({
    selector: 'jhi-sys-shopping-car-detail',
    templateUrl: './sys-shopping-car-detail.component.html'
})
export class SysShoppingCarDetailComponent implements OnInit {
    sysShoppingCar: ISysShoppingCar;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysShoppingCar }) => {
            this.sysShoppingCar = sysShoppingCar;
        });
    }

    previousState() {
        window.history.back();
    }
}
