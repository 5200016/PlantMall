import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysShoppingCar } from 'app/shared/model/sys-shopping-car.model';
import { SysShoppingCarService } from './sys-shopping-car.service';
import { SysShoppingCarComponent } from './sys-shopping-car.component';
import { SysShoppingCarDetailComponent } from './sys-shopping-car-detail.component';
import { SysShoppingCarUpdateComponent } from './sys-shopping-car-update.component';
import { SysShoppingCarDeletePopupComponent } from './sys-shopping-car-delete-dialog.component';
import { ISysShoppingCar } from 'app/shared/model/sys-shopping-car.model';

@Injectable({ providedIn: 'root' })
export class SysShoppingCarResolve implements Resolve<ISysShoppingCar> {
    constructor(private service: SysShoppingCarService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysShoppingCar> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysShoppingCar>) => response.ok),
                map((sysShoppingCar: HttpResponse<SysShoppingCar>) => sysShoppingCar.body)
            );
        }
        return of(new SysShoppingCar());
    }
}

export const sysShoppingCarRoute: Routes = [
    {
        path: 'sys-shopping-car',
        component: SysShoppingCarComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingCar.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-shopping-car/:id/view',
        component: SysShoppingCarDetailComponent,
        resolve: {
            sysShoppingCar: SysShoppingCarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingCar.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-shopping-car/new',
        component: SysShoppingCarUpdateComponent,
        resolve: {
            sysShoppingCar: SysShoppingCarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingCar.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-shopping-car/:id/edit',
        component: SysShoppingCarUpdateComponent,
        resolve: {
            sysShoppingCar: SysShoppingCarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingCar.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysShoppingCarPopupRoute: Routes = [
    {
        path: 'sys-shopping-car/:id/delete',
        component: SysShoppingCarDeletePopupComponent,
        resolve: {
            sysShoppingCar: SysShoppingCarResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingCar.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
