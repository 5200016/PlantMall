/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysRoleUpdateComponent } from 'app/entities/sys-role/sys-role-update.component';
import { SysRoleService } from 'app/entities/sys-role/sys-role.service';
import { SysRole } from 'app/shared/model/sys-role.model';

describe('Component Tests', () => {
    describe('SysRole Management Update Component', () => {
        let comp: SysRoleUpdateComponent;
        let fixture: ComponentFixture<SysRoleUpdateComponent>;
        let service: SysRoleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRoleUpdateComponent]
            })
                .overrideTemplate(SysRoleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysRoleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysRoleService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysRole(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysRole = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysRole();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysRole = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
