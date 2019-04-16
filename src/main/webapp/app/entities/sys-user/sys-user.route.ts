import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysUser } from 'app/shared/model/sys-user.model';
import { SysUserService } from './sys-user.service';
import { SysUserComponent } from './sys-user.component';
import { SysUserDetailComponent } from './sys-user-detail.component';
import { SysUserUpdateComponent } from './sys-user-update.component';
import { SysUserDeletePopupComponent } from './sys-user-delete-dialog.component';
import { ISysUser } from 'app/shared/model/sys-user.model';

@Injectable({ providedIn: 'root' })
export class SysUserResolve implements Resolve<ISysUser> {
    constructor(private service: SysUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysUser>) => response.ok),
                map((sysUser: HttpResponse<SysUser>) => sysUser.body)
            );
        }
        return of(new SysUser());
    }
}

export const sysUserRoute: Routes = [
    {
        path: 'sys-user',
        component: SysUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-user/:id/view',
        component: SysUserDetailComponent,
        resolve: {
            sysUser: SysUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-user/new',
        component: SysUserUpdateComponent,
        resolve: {
            sysUser: SysUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-user/:id/edit',
        component: SysUserUpdateComponent,
        resolve: {
            sysUser: SysUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysUserPopupRoute: Routes = [
    {
        path: 'sys-user/:id/delete',
        component: SysUserDeletePopupComponent,
        resolve: {
            sysUser: SysUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
