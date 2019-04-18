import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysRecharge } from 'app/shared/model/sys-recharge.model';
import { SysRechargeService } from './sys-recharge.service';
import { SysRechargeComponent } from './sys-recharge.component';
import { SysRechargeDetailComponent } from './sys-recharge-detail.component';
import { SysRechargeUpdateComponent } from './sys-recharge-update.component';
import { SysRechargeDeletePopupComponent } from './sys-recharge-delete-dialog.component';
import { ISysRecharge } from 'app/shared/model/sys-recharge.model';

@Injectable({ providedIn: 'root' })
export class SysRechargeResolve implements Resolve<ISysRecharge> {
    constructor(private service: SysRechargeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysRecharge> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysRecharge>) => response.ok),
                map((sysRecharge: HttpResponse<SysRecharge>) => sysRecharge.body)
            );
        }
        return of(new SysRecharge());
    }
}

export const sysRechargeRoute: Routes = [
    {
        path: 'sys-recharge',
        component: SysRechargeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRecharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-recharge/:id/view',
        component: SysRechargeDetailComponent,
        resolve: {
            sysRecharge: SysRechargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRecharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-recharge/new',
        component: SysRechargeUpdateComponent,
        resolve: {
            sysRecharge: SysRechargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRecharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-recharge/:id/edit',
        component: SysRechargeUpdateComponent,
        resolve: {
            sysRecharge: SysRechargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRecharge.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysRechargePopupRoute: Routes = [
    {
        path: 'sys-recharge/:id/delete',
        component: SysRechargeDeletePopupComponent,
        resolve: {
            sysRecharge: SysRechargeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysRecharge.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
