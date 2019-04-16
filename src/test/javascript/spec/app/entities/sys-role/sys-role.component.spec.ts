/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysRoleComponent } from 'app/entities/sys-role/sys-role.component';
import { SysRoleService } from 'app/entities/sys-role/sys-role.service';
import { SysRole } from 'app/shared/model/sys-role.model';

describe('Component Tests', () => {
    describe('SysRole Management Component', () => {
        let comp: SysRoleComponent;
        let fixture: ComponentFixture<SysRoleComponent>;
        let service: SysRoleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRoleComponent],
                providers: []
            })
                .overrideTemplate(SysRoleComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysRoleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysRoleService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysRole(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysRoles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
